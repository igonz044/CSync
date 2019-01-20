package com.example.csync;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;
import android.view.View;

/**
 * Created by Ronan on 9/16/2017.
 */

public abstract class AbsRuntimePermission extends Activity{

    private SparseIntArray ErrorString;
    /* private SensorManager mSensorManager;
     private Sensor mPressure;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ErrorString = new SparseIntArray();
    }

    //protected abstract void onPause(Bundle savedInstanceState);

    public abstract void onPermissionGranted(int requestCode);


    public void requestAppPermission(final String[]requestedPermissions, final int stringID, final int requestCode)
    {
        ErrorString.put(requestCode, stringID);

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean showRequestPermissions = false;
        for(String permissions:requestedPermissions)

        {
            permissionCheck += ContextCompat.checkSelfPermission(this, permissions);
            showRequestPermissions = showRequestPermissions || ActivityCompat.shouldShowRequestPermissionRationale(this, permissions);
        }
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (showRequestPermissions) {
                Snackbar.make(findViewById(android.R.id.content), stringID,
                        Snackbar.LENGTH_INDEFINITE).setAction("GRANT", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityCompat.requestPermissions(AbsRuntimePermission.this, requestedPermissions, requestCode);
                    }
                }).show();
            }
            else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        }
        else
        {
            onPermissionGranted(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for(int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if((grantResults.length > 0) && PackageManager.PERMISSION_GRANTED == permissionCheck){
            onPermissionGranted(requestCode);
        }
        else {
            //display message when contain some dangerous permission not accepted
            Snackbar.make(findViewById(android.R.id.content), ErrorString.get(requestCode),
                    Snackbar.LENGTH_INDEFINITE).setAction("ENABLE", new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent i = new Intent();
                    i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    i.setData(Uri.parse("package:" + getPackageName()));
                    i.addCategory(Intent.CATEGORY_DEFAULT);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(i);
                }
            }).show();
        }
    }
}
