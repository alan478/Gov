package com.example.VyAppar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

public class Activity_LOGIN extends AppCompatActivity {
    FrameLayout fl;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fl=findViewById(R.id.frame);
        fragmentManager=getSupportFragmentManager();
        SetFragment(new Fragment_Signin());
    }

    private void SetFragment(Fragment fragment)
    {fragmentManager.beginTransaction().replace(fl.getId(),fragment).commit();}
}