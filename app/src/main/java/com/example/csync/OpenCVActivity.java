package com.example.csync;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class OpenCVActivity extends AppCompatActivity {
    
    private static final int  SELECT_IMAGE = 10;
    private static final int VISION_ACTIVITY = 692;
    private ImageView viewSelectedImage;
    private Bitmap image;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                Uri intentData = data.getData();
                String path = intentData.getPath();
                Intent parse = new Intent(OpenCVActivity.this, GoogleVisionActivity.class);
                parse.putExtra(getResources().getString(R.string.Start_Vision_Intent), path);
                startActivityForResult(parse, VISION_ACTIVITY);
            }
        }
        else if(requestCode == VISION_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                Intent returnIntent = new Intent(OpenCVActivity.this, MainActivity.class);
                String text = data.getStringExtra(this.getResources().getString(R.string.Return_Vision_Intent));
                returnIntent.putExtra(getResources().getString(R.string.Return_OpenCV_Intent),text);
                startActivity(returnIntent);
            }
        }
//        try {
//            InputStream imageStream = getContentResolver().openInputStream(intentData);
//            image = BitmapFactory.decodeStream(imageStream);
//            viewSelectedImage.setImageBitmap(image);
//
//            //OpenCV code
//                Bitmap temp = image.copy(Bitmap.Config.ARGB_8888, true);
//                Mat calendar = new Mat();
//                Utils.bitmapToMat(temp, calendar);
//            */
//        }
//        catch (FileNotFoundException e){
//            //TODO catch some shit
//            e.printStackTrace();
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cv);
        viewSelectedImage = findViewById(R.id.imageView2);
    }

    public void onSelectClick(View v){

        Intent parse = new Intent(OpenCVActivity.this, FireBaseVisionActivity.class);
        startActivityForResult(parse, VISION_ACTIVITY);

        /*Intent selectPicture = new Intent(Intent.ACTION_PICK);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
        Uri dir = Uri.parse(path);
        selectPicture.setDataAndType(dir, "image/*");
        startActivityForResult(selectPicture, SELECT_IMAGE);*/

    }
}
