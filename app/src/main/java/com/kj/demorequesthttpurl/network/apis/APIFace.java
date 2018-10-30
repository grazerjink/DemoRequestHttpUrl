package com.kj.demorequesthttpurl.network.apis;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.kj.demorequesthttpurl.model.UserModel;
import com.kj.demorequesthttpurl.network.URLContentManager;
import com.kj.demorequesthttpurl.network.interfaces.IRequestType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIFace implements IBaseAPIs {

    private URLContentManager manager = new URLContentManager();

    public void detect(Bitmap bitmap, final URLContentManager.Callback<UserModel> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("data", bitmap);
        manager.sendBaseRequest(API_FACE_DETECT, METHOD_POST, params, IRequestType.OCTET_STREAM_DATA, new URLContentManager.ResponseCallback() {

            @Override
            public void onResponse(final boolean success, JSONObject jsonObject, final String message) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (success) {
                            callback.onResult(success, null, null);
                        } else {
                            callback.onResult(success, null, message);
                        }
                    }
                });
            }
        });
    }

}
