package com.example.filedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button startButton;
    private TextView progressDisplay;
    private volatile boolean stopThread = false;
    private volatile boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        progressDisplay = findViewById(R.id.progressDisplay);
    }

    private void resetDownload() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("Start");
                progressDisplay.setText("");
            }
        });
        isRunning = false;
    }

    public void mockFileDownloader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("DOWNLOADING...");
            }
        });

        for (int downloadProgress=0; downloadProgress <= 100; downloadProgress += 10) {
            if (stopThread) {
                resetDownload();
                return;
            }

            String progressMessage = "Download Progress: "+downloadProgress+"%";
            Log.d(TAG,progressMessage);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDisplay.setText(progressMessage);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        resetDownload();
    }

    class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            mockFileDownloader();
        }
    }

    public void startDownload(View view) {
        stopThread = false;
        if (!isRunning) {
            isRunning = true;
            ExampleRunnable runnable = new ExampleRunnable();
            new Thread(runnable).start();
        }
    }

    public void stopDownload(View view) {
        stopThread = true;
    }
}