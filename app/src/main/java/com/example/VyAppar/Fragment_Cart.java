package com.example.VyAppar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_Cart extends Fragment implements Adapter_Cart.CountandPrice {
    FragmentManager fragmentManager;
    Toolbar toolbar;
    Button btn;
    View view;
    BottomNavigationView bnw;
    RecyclerView l1;
    Adapter_Cart adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView tp,tc;


    public Fragment_Cart() {
        //empty constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_cart, container, false);
        fragmentManager=getActivity().getSupportFragmentManager();
        toolbar=view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        btn=view.findViewById(R.id.button2);
        tc=view.findViewById(R.id.total);
        tp=view.findViewById(R.id.tvtotalprice);
        l1=view.findViewById(R.id.l1);
        layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        adapter=new Adapter_Cart(getContext(),APPLICATION_CLASS.cart,this);
        l1.setLayoutManager(layoutManager);
        l1.setAdapter(adapter);
        checkNull();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tc.getText().toString().equals("")||tc.getText().toString().equals("0 items"))
                {
                    Toast.makeText(getContext(),"Please select atleast one item!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment_Payment()).commit();
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bnw=getActivity().findViewById(R.id.bottomnw);
                bnw.setSelectedItemId(R.id.searchbottom);

            }
        });


        return view;
    }


    @Override
    public void getTotalCountandPrice(String totalcount, String totalprice) {
        tc.setText(totalcount+" items");
        tp.setText("Total Price: Rs." + totalprice);
        if(tc.getText().toString().equals("0 items"))
        {
            tp.setText("");
        }
        APPLICATION_CLASS.TOTAL_PRICE=totalprice;
        APPLICATION_CLASS.TOTAL_QUANTITY=totalcount;
    }

    private boolean checkNull(){
        if(tc.getText().equals("Null"))
        {
            tp.setText("");
            tc.setText("0 items");
            APPLICATION_CLASS.TOTAL_PRICE="";
            APPLICATION_CLASS.TOTAL_QUANTITY="";

            return true;
        }
        else
        {
            return false;
        }
    }
}