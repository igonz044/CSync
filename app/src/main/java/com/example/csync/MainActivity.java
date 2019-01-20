package com.example.csync;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AbsRuntimePermission {

    private static final int REQUEST_PERMISSION = 10; //not too sure why this is 10 so be careful

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAppPermission(new String[]{
                                            Manifest.permission.CAMERA,
                                            Manifest.permission.READ_EXTERNAL_STORAGE},
                            R.string.Permission_Text, REQUEST_PERMISSION);
        Intent imageSelect = new Intent(MainActivity.this, OpenCVActivity.class);
        startActivity(imageSelect);
    }
    //KEEP THIS FOR THE APP PERMISSIONS
    @Override
    public void onPermissionGranted(int requestCode)
    {

    }
}
