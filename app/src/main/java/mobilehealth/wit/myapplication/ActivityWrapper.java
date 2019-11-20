package mobilehealth.wit.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWrapper extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle bundle){
        super.onCreate(bundle);
        StartActivity.setCurrentActivity(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        StartActivity.setCurrentActivity(this);
    }
}