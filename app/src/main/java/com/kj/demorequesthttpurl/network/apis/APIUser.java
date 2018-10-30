package com.kj.demorequesthttpurl.network.apis;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.kj.demorequesthttpurl.model.UserModel;
import com.kj.demorequesthttpurl.network.URLContentManager;
import com.kj.demorequesthttpurl.network.interfaces.IRequestType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.kj.demorequesthttpurl.network.interfaces.IRequestType.FORM_DATA;

public class APIUser implements IBaseAPIs {

    private URLContentManager manager = new URLContentManager();

    public void login(String email, String password, final URLContentManager.Callback<UserModel> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        manager.sendBaseRequest(API_LOGIN, METHOD_POST, params, IRequestType.RAW_DATA, new URLContentManager.ResponseCallback() {
            @Override
            public void onResponse(final boolean success, final JSONObject jsonObject, final String message) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (success) {
                            UserModel userModel = new UserModel(jsonObject);
                            Log.e("", "onResponse: " + userModel.getAccessToken());
                            callback.onResult(true, userModel, null);
                        } else {
                            callback.onResult(false, null, message);
                        }
                    }
                });
            }
        });
    }

    public void updateProfile(Bitmap bitmap, final URLContentManager.Callback<String> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("FullName", "Khai Quan 2222");
        params.put("Gender", 0);
        params.put("DateOfBirth", "2006-05-20 00:00:00");
        params.put("Profession", "iOS Developer");
        params.put("Email", "khaiquan1996@gmail.com");
        params.put("type", "avatar");
        params.put("Files", bitmap);
//        params.put("message", "Demo message 1");
//        params.put("title", "Demo title 1");
//        params.put("image1", bitmap);
//        params.put("image2", bitmap);
//        params.put("image3", bitmap);
        manager.sendBaseRequest(API_UPDATE_PROFILE, METHOD_POST, params, FORM_DATA, new URLContentManager.ResponseCallback() {
            @Override
            public void onResponse(final boolean success, final JSONObject jsonObject, final String message) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (success) {
                            UserModel userModel = new UserModel(jsonObject);
                            Log.e("", "onResponse: " + userModel.getAccessToken());
                            callback.onResult(true, jsonObject.toString(), null);
                        } else {
                            callback.onResult(false, null, message);
                        }
                    }
                });
            }
        });
    }
}