package com.kj.demorequesthttpurl;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kj.demorequesthttpurl.model.TaxGroupModel;
import com.kj.demorequesthttpurl.model.UserModel;
import com.kj.demorequesthttpurl.network.apis.APIFace;
import com.kj.demorequesthttpurl.network.apis.APITaxGroup;
import com.kj.demorequesthttpurl.network.apis.APIUser;
import com.kj.demorequesthttpurl.network.apis.IBaseAPIs;
import com.kj.demorequesthttpurl.network.URLContentManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private IBaseAPIs baseAPIs;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        baseAPIs = new APIUser();
//        ((APIUser) baseAPIs).login("mgmt@saaspiens.com", "123456", new URLContentManager.Callback<UserModel>() {
//            @Override
//            public void onResult(boolean success, UserModel data, String message) {
//                if (success) {
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                    createTaxGroup();
//                } else {
//                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        detect();
//        updateProfile();

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Downloading...");
        progressDialog.setProgressNumberFormat("%1d MB / %2d MB");
        progressDialog.setProgress(0);
        downloadStream();
    }

    //    private void createTaxGroup() {
//        TaxGroupModel taxGroupModel = new TaxGroupModel();
//        taxGroupModel.setName("Val " + 1 + "%");
//        taxGroupModel.setType(1);
//        taxGroupModel.setValue(1);
//        baseAPIs = new APITaxGroup();
//        ((APITaxGroup) baseAPIs).createTaxGroup(taxGroupModel, new URLContentManager.Callback<TaxGroupModel>() {
//            @Override
//            public void onResult(boolean success, TaxGroupModel data, String message) {
//                if (success) {
//                    Toast.makeText(MainActivity.this, "Success: " + data.getName(), Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//    }
//
//    private void detect() {
//        Bitmap bitmap = ((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.girl)).getBitmap();
//        baseAPIs = new APIFace();
//        ((APIFace) baseAPIs).detect(bitmap, new URLContentManager.Callback<UserModel>() {
//            @Override
//            public void onResult(boolean success, UserModel data, String message) {
//
//            }
//        });
//    }
//
//    private void updateProfile() {
//        Bitmap bitmap = ((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.background)).getBitmap();
//        baseAPIs = new APIUser();
//        ((APIUser) baseAPIs).updateProfile(bitmap, new URLContentManager.Callback<String>() {
//            @Override
//            public void onResult(boolean success, String data, String message) {
//
//            }
//        });
//    }

    private void downloadStream() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(IBaseAPIs.API_VIDEO_DATA);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod(IBaseAPIs.METHOD_GET);
                    int code = httpURLConnection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        streamToBinary(httpURLConnection.getInputStream(), httpURLConnection.getContentLength());
                    } else {
                        String message = httpURLConnection.getResponseMessage();
                        Log.e("", "downloadStream:  Failed! " + message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void streamToBinary(InputStream is, final long dataLength) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
                progressDialog.setMax(byteToMB(dataLength));
            }
        });
        int bytesRead;
        byte[] buffer = new byte[4096];
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = is.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.setProgress(byteToMB(output.size()));
                        Log.e("", String.format("Read bytes:" + output.size() + "/" + dataLength));
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int byteToMB(long byteTransform) {
        long mb = 1024L * 1024L;
        return (int) (byteTransform / mb);

    }
}
