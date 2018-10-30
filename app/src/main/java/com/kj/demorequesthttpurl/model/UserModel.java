package com.kj.demorequesthttpurl.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel implements IBaseModel {

    private int status;
    private String message;
    private String accessToken;
    private String tokenType;

    public UserModel(JSONObject jsonObject) {
        try {
            status = jsonObject.getInt("status");
            accessToken = jsonObject.getString("access_token");
            message = jsonObject.getString("message");
            tokenType = jsonObject.getString("token_type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
