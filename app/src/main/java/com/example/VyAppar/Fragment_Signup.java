package com.example.VyAppar;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Fragment_Signup extends Fragment {

    FragmentManager fragmentManager;
    FrameLayout parentfl;
    TextView tvEmail,tvPass,tvsignin,tvconfirm;
    Button btnRegister;
    View view;
    FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";





    public Fragment_Signup() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager=getActivity().getSupportFragmentManager();
        parentfl=getActivity().findViewById(R.id.frame);
        tvEmail=view.findViewById(R.id.tvEmail);
        tvPass=view.findViewById(R.id.editTextPassword);
        tvconfirm=view.findViewById(R.id.tvpassconfirm);
        tvsignin=view.findViewById(R.id.tvSignIn);
        btnRegister=view.findViewById(R.id.button);
        fragmentManager=getActivity().getSupportFragmentManager();
        parentfl=getActivity().findViewById(R.id.frame);
        firebaseAuth=FirebaseAuth.getInstance();
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(new Fragment_Signin());
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AllAreEmpty(tvEmail,tvPass,tvconfirm))
                {
                    Toast.makeText(getContext(),"Please Enter all Fields",Toast.LENGTH_SHORT).show();
                }
                else if(!tvEmail.getText().toString().matches(emailPattern))
                {
                    Toast.makeText(getContext(),"Please Enter a valid Email",Toast.LENGTH_SHORT).show();
                }
                    else if(tvPass.getText().toString().length()<8)
                     {
                    Toast.makeText(getContext(),"Password should be atleast 8 characters!",Toast.LENGTH_SHORT).show();
                      }
                else if(!tvPass.getText().toString().equals(tvconfirm.getText().toString()))
                {
                    Toast.makeText(getContext(),"Password should match!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.createUserWithEmailAndPassword(tvEmail.getText().toString(),tvPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getContext(),"Succesfully created a User",Toast.LENGTH_SHORT).show();
                                        SetFragment(new Fragment_Signin());
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(),"Error user not Created",Toast.LENGTH_SHORT).show();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    private void SetFragment(Fragment fragment)
    {fragmentManager.beginTransaction().replace(parentfl.getId(),fragment).commit();}

    private boolean AllAreEmpty(TextView tv1,TextView tv2,TextView tv3)
    {
        String s1=tv1.getText().toString();
        String s2=tv2.getText().toString();
        String s3=tv3.getText().toString();
        if(s1.isEmpty()||s2.isEmpty()||s3.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }

    }


}