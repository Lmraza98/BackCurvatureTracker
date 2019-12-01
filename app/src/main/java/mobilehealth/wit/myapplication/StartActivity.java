package mobilehealth.wit.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private static ActivityWrapper currentActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final SharedPreferences preferences = this.getSharedPreferences("default_prefs", 0);
        final boolean firstLoad = !preferences.getBoolean("completedSetup", false);
        if(firstLoad) {
            final Intent intent = new Intent(this, BluetoothActivity.class);
            startActivity(intent);
        } else {
            final Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        startActivity(currentActivity.getIntent());
        finish();

    }

    public static ActivityWrapper getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(ActivityWrapper currentActivity1) {
        currentActivity = currentActivity1;
    }
}