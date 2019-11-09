package com.jammi.akash.schoolbustracker.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.jammi.akash.schoolbustracker.Fragement.Profile_edit;
import com.jammi.akash.schoolbustracker.Fragement.Settings;
import com.jammi.akash.schoolbustracker.Fragement.notification;
import com.jammi.akash.schoolbustracker.R;

public class menu_Functions extends AppCompatActivity {
    final public static int TAG_SETTINGS=1;
    final public static int TAG_PROFILE=2;
    final public static int TAG_NOTIFICATION=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu__functions);

        int guider = getIntent().getIntExtra("option_no", 1);
        Log.e("guider value",""+guider);

            switch (guider) {
                case  TAG_SETTINGS:
                    Fragment fragment= new Settings();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment).commit();
                    break;
                    case  TAG_PROFILE:
                    Fragment sfragment= new Profile_edit();
                    FragmentManager sfragmentManager = getSupportFragmentManager();
                    sfragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, sfragment).commit();
                    break;

                    case  TAG_NOTIFICATION:
                    Fragment nfragment= new notification();
                    FragmentManager nfragmentManager = getSupportFragmentManager();
                    nfragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, nfragment).commit();
                    break;

            }
    }
}
