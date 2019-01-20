package com.example.csync;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VisionActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE = 38;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_acitvity);

    }
    public void onSelectClick(View v){

        Intent selectPicture = new Intent(Intent.ACTION_PICK);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
        Uri dir = Uri.parse(path);
        selectPicture.setDataAndType(dir, "image/*");
        startActivityForResult(selectPicture, SELECT_IMAGE);
    }

    public void onPlannerClick(View v) {
        Intent vision = new Intent(VisionActivity.this, EditActivity.class);
        startActivity(vision);
    }

}
