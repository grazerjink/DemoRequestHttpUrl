package com.kj.demorequesthttpurl.network.apis;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.kj.demorequesthttpurl.model.TaxGroupModel;
import com.kj.demorequesthttpurl.model.UserModel;
import com.kj.demorequesthttpurl.network.URLContentManager;
import com.kj.demorequesthttpurl.network.interfaces.IRequestType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APITaxGroup implements IBaseAPIs {

    private URLContentManager manager = new URLContentManager();

    public void createTaxGroup(TaxGroupModel taxGroup, final URLContentManager.Callback<TaxGroupModel> callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", taxGroup.getName());
        params.put("type", taxGroup.getType());
        params.put("value", taxGroup.getValue());

        manager.sendBaseRequest(API_CREATE_TAX_GROUP, METHOD_POST, params, IRequestType.RAW_DATA, new URLContentManager.ResponseCallback() {
            @Override
            public void onResponse(final boolean success, final JSONObject jsonObject, final String message) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (success) {
                            TaxGroupModel taxGroupModel = new TaxGroupModel(jsonObject);
                            Log.e("", "onResponse: " + taxGroupModel);
                            callback.onResult(true, taxGroupModel, null);
                        } else {
                            callback.onResult(false, null, message);
                        }
                    }
                });
            }
        });
    }

}
