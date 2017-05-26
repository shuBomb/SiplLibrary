package com.androidutils;

import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 05-10-2016.
 */

public class VideoFileOperations {

    public static File getVideoDirectory(Context context){
        File outputDir=null;
        String externalStorageState= Environment.getExternalStorageState();
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        }
        return outputDir;
    }

    public static File generateTimeStampVideoUri(Context context){
        File videoFileUri=null;
        File outputDir=getVideoDirectory(context);
        if(outputDir!=null){
            String timeStamp=new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date());
            String vidFileName="VID_"+ timeStamp +".mp4";
            videoFileUri=new File(outputDir,vidFileName);

        }
        return videoFileUri;
    }

    public static MediaRecorder createAndPrepareVideoRecorder(Context context, Camera camera, int cameraId, int camcoderProfile, String outputFile){

        if(camera==null)
            return null;

        MediaRecorder mediaRecorder=new MediaRecorder();

        camera.unlock();
        mediaRecorder.setCamera(camera);

        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setMaxDuration(15000);

        mediaRecorder.setProfile(CamcorderProfile.get(camcoderProfile));

        mediaRecorder.setOutputFile(outputFile);

        try {
            mediaRecorder.prepare();
        } catch (Exception e) {
            String message="Error in Media Prepare"+e.getMessage();
            Log.e("TAG" ,message);
            Toast.makeText(context,message, Toast.LENGTH_LONG).show();
            releaseVideoRecorder(mediaRecorder,camera);
            mediaRecorder=null;
        }

        return  mediaRecorder;
    }

    public static void releaseVideoRecorder(MediaRecorder mediaRecorder, Camera camera){

        if(mediaRecorder==null)
            return;

        mediaRecorder.reset(); // clear recorder configuration
        mediaRecorder.release(); // release the recorder object
        camera.lock(); // lock camera for later use
    }
}
