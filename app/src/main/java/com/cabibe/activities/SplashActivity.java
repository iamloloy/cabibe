package com.cabibe.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cabibe.R;

/**
 * Created by ekxia on 5/2/2018.
 */

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(MainActivity.newIntent(SplashActivity.this));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
