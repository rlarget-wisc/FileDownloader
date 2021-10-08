package com.example.filedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mockFileDownloader() {
        for (int downloadProgress=0; downloadProgress <= 100; downloadProgress += 10) {
            Log.d(TAG,"Download Progress: "+downloadProgress+"%");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void startDownload(View view) {
        mockFileDownloader();
    }

    public void stopDownload(View view) {
    }
}