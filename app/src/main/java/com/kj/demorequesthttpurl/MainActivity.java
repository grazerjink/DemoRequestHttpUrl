package com.kj.demorequesthttpurl;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kj.demorequesthttpurl.model.TaxGroupModel;
import com.kj.demorequesthttpurl.model.UserModel;
import com.kj.demorequesthttpurl.network.apis.APIFace;
import com.kj.demorequesthttpurl.network.apis.APITaxGroup;
import com.kj.demorequesthttpurl.network.apis.APIUser;
import com.kj.demorequesthttpurl.network.apis.IBaseAPIs;
import com.kj.demorequesthttpurl.network.URLContentManager;

public class MainActivity extends AppCompatActivity {

    private IBaseAPIs baseAPIs;

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

}
