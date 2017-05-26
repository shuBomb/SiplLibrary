package com.androidutils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Admin on 13-01-2016.
 */
 public class DownloadFileFromURL extends AsyncTask<String, String, String> {

    Context context;
    String songTitle = "";
    int songPositionInList;
    boolean downloadFailed = false;
    int notificationId;

    public DownloadFileFromURL(Context context, String songTitle, int positionDownload, int notifId){
        this.context = context;
        this.songTitle = songTitle;
        songPositionInList = positionDownload;
        this.notificationId = notificationId;
    }

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //ShowToast.showShortToast(context, context.getString(R.string.downloading));
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            OutputStream output = new FileOutputStream("/sdcard/"+songTitle+".mp3");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                int progress = (int) ((total * 100) / lenghtOfFile);

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            downloadFailed = true;
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(String file_url) {

        if(!downloadFailed) {
            String songPath = Environment.getExternalStorageDirectory().toString() + "/" + songTitle +
                    ".mp3";
        }
    }

}
