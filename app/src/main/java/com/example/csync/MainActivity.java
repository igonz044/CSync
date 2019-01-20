package com.example.csync;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AbsRuntimePermission {

    private static final int REQUEST_PERMISSION = 10; //not too sure why this is 10 so be careful

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAppPermission(new String[]{
                        Manifest.permission.CAMERA},
                //android.Manifest.permission.BODY_SENSORS},
                R.string.Permission_Text, REQUEST_PERMISSION);

    }
    //KEEP THIS FOR THE APP PERMISSIONS
    @Override
    public void onPermissionGranted(int requestCode)
    {

    }
}
