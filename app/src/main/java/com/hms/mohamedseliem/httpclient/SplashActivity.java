package com.hms.mohamedseliem.httpclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by WIN\c00300901 on 11/5/17.
 */

public class SplashActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start home activity
        //startActivity(new Intent(SplashActivity.this, MainActivity.class));

        // close splash activity
        //finish();
        setContentView(R.layout.layout);
        gifImageView=(GifImageView) findViewById(R.id.gifImageView);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.VISIBLE);
        try{
            InputStream inputStream=getAssets().open("giphy (1).gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex)
        {

        }
        //wait for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                //for checking googleMapActivity
                //SplashActivity.this.startActivity(new Intent(SplashActivity.this,MapsActivity.class));
                SplashActivity.this.finish();
            }
        },3000);
    }
}