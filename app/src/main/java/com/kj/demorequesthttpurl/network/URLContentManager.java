package com.kj.demorequesthttpurl.network;


import android.graphics.Bitmap;
import android.util.Log;

import com.kj.demorequesthttpurl.network.apis.IBaseAPIs;
import com.kj.demorequesthttpurl.network.interfaces.IRequestType;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class URLContentManager {

    public interface Callback<T> {
        void onResult(boolean success, T data, String message);
    }

    public interface ResponseCallback {
        void onResponse(boolean success, JSONObject jsonObject, String message);
    }

    private String twoHyphens = "--";
    private String lineEnd = "\r\n";
    private String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
    private String startBodyString = twoHyphens + boundary + lineEnd;
    private String endBodyString = twoHyphens + boundary + twoHyphens;
    private StringBuilder dataLogging;

    public void sendBaseRequest(final String uri, final String method, final Map<String, Object> params, final int requestType, final ResponseCallback callback) {
        dataLogging = new StringBuilder();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(uri);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestMethod(method);

                    httpURLConnection.addRequestProperty("Authorization", "Bearer " + IBaseAPIs.TOKEN);
                    dataLogging.append("Header: " + "Authorization: Bearer " + IBaseAPIs.TOKEN);

//                    httpURLConnection.addRequestProperty("token", IBaseAPIs.TOKEN);
//                    dataLogging.append("Header: " + "token:  " + IBaseAPIs.TOKEN);


                    if (requestType == IRequestType.RAW_DATA) {

                        dataLogging.append("Header: " + "Content-Type: application/json").append(lineEnd).append(lineEnd);

                        httpURLConnection.addRequestProperty("Content-Type", "application/json");

                        OutputStreamWriter outStream = new OutputStreamWriter(httpURLConnection.getOutputStream());
                        String rawData = new JSONObject(params).toString();
                        Log.e("", "Request data: " + rawData);
                        outStream.write(rawData);
                        outStream.close();

                    } else if (requestType == IRequestType.FORM_DATA) {

//                        dataLogging.append("Header: " + "Content-Type: multipart/form-data; boundary=").append(boundary).append(lineEnd).append(lineEnd);
                        httpURLConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                        DataOutputStream outStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        for (String key : params.keySet()) {
                            Object obj = params.get(key);
                            if (obj instanceof ArrayList) {
                                for (Object subObj : (ArrayList) obj) {
                                    if (subObj instanceof Bitmap) {
                                        createUploadFileParam(key, (Bitmap) subObj, outStream);
                                    } else {
                                        createPrimitiveParam(key, subObj, outStream);
                                    }
                                }
                            } else if (obj instanceof Bitmap) {
                                createUploadFileParam(key, (Bitmap) obj, outStream);
                            } else {
                                createPrimitiveParam(key, obj, outStream);
                            }
                        }

                        dataLogging.append(endBodyString);

                        outStream.writeBytes(endBodyString);
                        outStream.flush();
                        outStream.close();
                    } else {
                        // Octet stream
                        dataLogging.append("Header: " + "Content-Type: application/octet-stream").append(lineEnd).append(lineEnd);
                        dataLogging.append("Header: " + "ocp-apim-subscription-key: ad1e5d0cb6f64ee3998a3efe9b49738a").append(lineEnd).append(lineEnd);

                        httpURLConnection.addRequestProperty("Content-Type", "application/octet-stream");
                        httpURLConnection.addRequestProperty("ocp-apim-subscription-key", "ad1e5d0cb6f64ee3998a3efe9b49738a");

                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        Bitmap bitmap = (Bitmap) params.get("data");
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                        outputStream.close();
                    }

                    Log.e("", "===== Request body:\r\n" + dataLogging.toString());

                    String data;
                    int code = httpURLConnection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        data = streamToString(httpURLConnection.getInputStream());
                        callback.onResponse(true, new JSONObject(data), null);
                    } else {
                        data = streamToString(httpURLConnection.getErrorStream());
                        String message = httpURLConnection.getResponseMessage();
                        callback.onResponse(false, new JSONObject(data), message);

                        Log.e("", "===== Raw response body:\r\n" + message);
                    }

                    Log.e("", "===== Response body:\r\n" + data);

                    httpURLConnection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void createPrimitiveParam(String key, Object value, DataOutputStream outputStream) {
        try {
            dataLogging.append(startBodyString);
            dataLogging.append("Content-Disposition: form-data; name=\"");
            dataLogging.append(key);
            dataLogging.append("\"");
            dataLogging.append(lineEnd);
            dataLogging.append(lineEnd);
            dataLogging.append(value);
            dataLogging.append(lineEnd);

            outputStream.writeBytes(startBodyString);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"");
            outputStream.writeBytes(key);
            outputStream.writeBytes("\"");
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(value.toString());
            outputStream.writeBytes(lineEnd);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUploadFileParam(String key, Bitmap bitmap, DataOutputStream outputStream) {
        try {

            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, arrayOutputStream);

            dataLogging.append(startBodyString);
            dataLogging.append("Content-Disposition: form-data; name=\"");
            dataLogging.append(key);
            dataLogging.append("\"; filename=\"photo.png\"");
            dataLogging.append(lineEnd);
            dataLogging.append("Content-Type: image/png").append(lineEnd);
            dataLogging.append(lineEnd);
            dataLogging.append(arrayOutputStream.toString());
            dataLogging.append(lineEnd);

            outputStream.writeBytes(startBodyString);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"");
            outputStream.writeBytes(key);
            outputStream.writeBytes("\"; filename=\"photo.png\"");
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes("Content-Type: image/png");
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.write(arrayOutputStream.toByteArray());
            outputStream.writeBytes(lineEnd);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getEncodedUrlString(String key, Object value) {
        return "&" + key + "=" + value.toString();
    }

    private String getFileEncodedByteArrayString(String key, Bitmap bitmapData) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapData.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            return "&" + key + "=" + new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String streamToString(InputStream is) throws IOException {
        String str = "";

        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();

            } finally {
                is.close();
            }
            str = sb.toString();
        }
        return str;
    }

}
