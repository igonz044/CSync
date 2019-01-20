package com.example.csync;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class OpenCVActivity extends AppCompatActivity {
    
    private static final int  SELECT_IMAGE = 1;
    private ImageView viewSelectedImage;
    private Bitmap image;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri intentData = data.getData();
        try {
            InputStream imageStream = getContentResolver().openInputStream(intentData);
            image = BitmapFactory.decodeStream(imageStream);
            viewSelectedImage.setImageBitmap(image);

            //OpenCV code
            /*
                Bitmap temp = image.copy(Bitmap.Config.ARGB_8888, true);
                Mat calendar = new Mat();
                Utils.bitmapToMat(temp, calendar);
            */
        }
        catch (FileNotFoundException e){
            //TODO catch some shit
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cv);
        viewSelectedImage = findViewById(R.id.imageView2);
    }

    public void onSelectClick(View v){

        Intent selectPicture = new Intent(Intent.ACTION_PICK);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
        Uri dir = Uri.parse(path);
        selectPicture.setDataAndType(dir, "image/*");
        startActivityForResult(selectPicture, SELECT_IMAGE);
    }
}
