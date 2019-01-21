
package com.example.csync;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.android.gms.common.api.CommonStatusCodes;
import com.guna.ocrlibrary.OCRCapture;

import java.io.IOException;
import java.util.List;

import static com.guna.ocrlibrary.OcrCaptureActivity.TextBlockObject;

public class GoogleVisionActivity  extends AppCompatActivity {
    private TextView textView;
    private final int CAMERA_SCAN_TEXT = 0;
    private final int LOAD_IMAGE_RESULTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.TextView);
        Intent intent = getIntent();
        Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, LOAD_IMAGE_RESULTS);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, LOAD_IMAGE_RESULTS);
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == CAMERA_SCAN_TEXT) {
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    textView.setText(data.getStringExtra(TextBlockObject));
                }
            } else if (requestCode == LOAD_IMAGE_RESULTS) {
                Uri pickedImage = data.getData();
                try {
                    //Extract text from image.
                    String text = OCRCapture.Builder(this).getTextFromUri(pickedImage);
                    //You can also use getTextFromBitmap(Bitmap bitmap) or getTextFromImage(String imagePath) buplic APIs from ibrary.
                    textView.setText(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


