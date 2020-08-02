package com.example.VyAppar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Activity_SPLASH extends AppCompatActivity {
    private static int SPLASH_LENGTH=2000;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        firebaseAuth=FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    FirebaseUser currentuser= firebaseAuth.getCurrentUser();
                    if(currentuser!=null)
                    {Intent intent=new Intent(Activity_SPLASH.this,Activity_MAIN.class);
                        startActivity(intent);
                        finish();}
                    else
                    {
                        Intent intent=new Intent(Activity_SPLASH.this, Activity_LOGIN.class);
                        startActivity(intent);
                        finish();
                    }
            }
        }, SPLASH_LENGTH);


    }

}