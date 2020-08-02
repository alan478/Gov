package com.example.VyAppar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment_Search extends Fragment {

    View view;
    Adapter_Search adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView l1;
    SearchView sw;




    public Fragment_Search() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        l1=view.findViewById(R.id.l1);
        l1.setHasFixedSize(true);
        adapter=new Adapter_Search(this.getActivity(),APPLICATION_CLASS.searchresults);
        linearLayoutManager=new LinearLayoutManager(this.getActivity(),RecyclerView.VERTICAL,false);
        l1.setAdapter(adapter);
        l1.setLayoutManager(linearLayoutManager);

        sw=(SearchView) view.findViewById(R.id.sw);
        sw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);

        return view;


    }


}