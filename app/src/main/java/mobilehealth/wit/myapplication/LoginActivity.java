package mobilehealth.wit.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import mobilehealth.wit.myapplication.fragments.OnFragmentInteractionListener;


public class LoginActivity extends AppCompatActivity implements OnFragmentInteractionListener{
   private FirebaseAuth auth;
   private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // auth = FirebaseAuth.getInstance();


        TextView signUp_text = findViewById(R.id.signUp_text);
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });



         Button login = findViewById(R.id.user_login);
         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 switch (v.getId())
                 {
                     case R.id.user_login:
                         Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                         startActivityForResult(signInIntent, RC_SIGN_IN);
                         break;
                 }
             }

         });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Result returned from launching the Intent from GoogleSignInClient.getSignInintent(...);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask)
    {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Signed in successfully, show authenticated UI.
            Intent i = new Intent(this, SetupActivity.class);

            i.putExtra("GOOGLE", account);
            startActivity(i);



            final SharedPreferences preferences = this.getSharedPreferences("default_prefs", 0);
            final boolean firstLoad = !preferences.getBoolean("completedSetup", false);
            if(firstLoad) {
                final Intent intent = new Intent(this, SetupActivity.class);
                startActivity(intent);
            } else {
                final Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            finish();

        } catch(ApiException e )
        {
            Log.w("TAG", "signInResult:Failed code=" + e.getStatusCode());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //UPDATE UI
        //updateUI(account);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}