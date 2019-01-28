package com.example.csync;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AbsRuntimePermission {

    private static final int REQUEST_PERMISSION = 10; //not too sure why this is 10 so be careful
    private static final int INTENT_CODE = 34932;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAppPermission(new String[]{
                                            Manifest.permission.CAMERA,
                                            Manifest.permission.READ_EXTERNAL_STORAGE},
                            R.string.Permission_Text, REQUEST_PERMISSION);
        Intent imageSelect = new Intent(MainActivity.this, OpenCVActivity.class);
        startActivityForResult(imageSelect,INTENT_CODE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == INTENT_CODE){
            if(resultCode == Activity.RESULT_OK){

            }
        }
    }

    //KEEP THIS FOR THE APP PERMISSIONS
    @Override
    public void onPermissionGranted(int requestCode)
    {

    }
}
