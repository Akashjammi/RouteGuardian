package com.jammi.akash.schoolbustracker.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.jammi.akash.schoolbustracker.CustomClass.PreferenceData;
import com.jammi.akash.schoolbustracker.R;

public class splash extends Activity {
    private static boolean splashLoaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!splashLoaded) {
            setContentView(R.layout.activity_splash);
            int secondsDelayed = 2;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if(PreferenceData.getUserLoggedInStatus(getApplicationContext()))
                    {
                    startActivity(new Intent(splash.this, parentmapActivity.class));
                    finish();
                    }else{
                        startActivity(new Intent(splash.this, MainActivity.class));
                        finish();

                    }
                }
            }, secondsDelayed * 500);

            splashLoaded = true;
        }
        else {
            Intent goToMainActivity = new Intent(splash.this, MainActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }
}
