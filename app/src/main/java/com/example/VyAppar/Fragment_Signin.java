package com.example.VyAppar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Fragment_Signin extends Fragment {

    TextView tvEmail,tvPass,tvPassreset,tvregister;
    Button btnLogin;
    View view;
    FragmentManager fragmentManager;
    FrameLayout parentfl;
    FirebaseAuth firebaseAuth;



    public Fragment_Signin() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvEmail=view.findViewById(R.id.tvEmail);
        tvPass=view.findViewById(R.id.editTextPassword);
        tvPassreset=view.findViewById(R.id.tvForgotPass);
        tvregister=view.findViewById(R.id.tvSignIn);
        btnLogin=view.findViewById(R.id.button);
        fragmentManager=getActivity().getSupportFragmentManager();
        parentfl=getActivity().findViewById(R.id.frame);
        firebaseAuth=FirebaseAuth.getInstance();


        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(new Fragment_Signup());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEmail.getText().toString().isEmpty()||tvPass.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"Please Enter all Fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.signInWithEmailAndPassword(tvEmail.getText().toString(),tvPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Intent intent=new Intent(getContext(),Activity_MAIN.class);
                                        startActivity(intent);
                                        Toast.makeText(getContext(),"Successfully Logged In",Toast.LENGTH_SHORT).show();
                                        getActivity().finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(),"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_signin, container, false);
        return (view);
    }

    private void SetFragment(Fragment fragment)
    {fragmentManager.beginTransaction().replace(parentfl.getId(),fragment).commit();}

    //Functions



}