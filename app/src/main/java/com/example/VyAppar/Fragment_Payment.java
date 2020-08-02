package com.example.VyAppar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_Payment extends Fragment {
    View view;
    ImageView back;
    BottomNavigationView bnw;


    public Fragment_Payment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_payment, container, false);
        back=view.findViewById(R.id.payment_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bnw=getActivity().findViewById(R.id.bottomnw);
                bnw.setSelectedItemId(R.id.cartbottom);
            }
        });
        return view;
    }
}