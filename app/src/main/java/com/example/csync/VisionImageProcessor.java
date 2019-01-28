/*
package com.example.csync;

import android.graphics.Bitmap;
import android.media.Image;
import org.apache.sysml.api.mlcontext.FrameMetadata;

import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.guna.ocrlibrary.camera.GraphicOverlay;

import java.nio.ByteBuffer;

*/
/** An inferface to process the images with different ML Kit detectors and custom image models. *//*

public interface VisionImageProcessor {

    */
/** Processes the images with the underlying machine learning models. *//*

    void process(ByteBuffer data, FrameMetadata frameMetadata, GraphicOverlay graphicOverlay)
            throws FirebaseMLException;

    */
/** Processes the bitmap images. *//*

    void process(Bitmap bitmap, GraphicOverlay graphicOverlay);

    */
/** Stops the underlying machine learning model and release resources. *//*

    void stop();
}*/
