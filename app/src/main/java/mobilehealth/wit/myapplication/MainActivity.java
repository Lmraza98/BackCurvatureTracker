package mobilehealth.wit.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInAccount account = getIntent().getParcelableExtra("GOOGLE");

       TextView text = findViewById(R.id.name);
       text.setText(account.getDisplayName());
    }
}
