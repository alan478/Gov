package com.example.VyAppar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

public class Adapter_Cart extends RecyclerView.Adapter<Adapter_Cart.newViewHolder> {

    private ArrayList<Class_Cart> carts=new ArrayList<>();
    int height;
    CountandPrice activity,fragment;


    public interface CountandPrice
    {
      void getTotalCountandPrice(String totalcount,String totalprice);

    }


    public Adapter_Cart(Context context, ArrayList<Class_Cart> list, CountandPrice reference){
        carts = list;
        activity=(CountandPrice)context;
        fragment=(CountandPrice) reference;



    }


    public class newViewHolder extends RecyclerView.ViewHolder {

        public ImageView iwdisp,add,remove;
        public TextView title,desc,price,quantity;

        public newViewHolder(@NonNull final View itemView) {
            super(itemView);

            WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
            layoutParams.height=height/4;
            itemView.setLayoutParams(layoutParams);
            add=itemView.findViewById(R.id.add);
            remove=itemView.findViewById(R.id.subtract);
            iwdisp=itemView.findViewById(R.id.imageView2);
            title=itemView.findViewById(R.id.tvTitle);
            desc=itemView.findViewById(R.id.tvDesc);
            price=itemView.findViewById(R.id.price);
            quantity=itemView.findViewById(R.id.numberofitems);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i=carts.indexOf(itemView.getTag());
                    carts.get(i).setQuantity((Integer.toString(Integer.parseInt(quantity.getText().toString())+1)));
                    notifyDataSetChanged();
                    fragment.getTotalCountandPrice(getCount(),getPrice());


                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i=carts.indexOf(itemView.getTag());
                    if(Integer.parseInt(quantity.getText().toString())==1)
                    {
                        carts.remove(i);
                        notifyDataSetChanged();
                        fragment.getTotalCountandPrice(getCount(),getPrice());

                    }
                    else {
                        carts.get(i).setQuantity((Integer.toString(Integer.parseInt(quantity.getText().toString()) - 1)));
                        notifyDataSetChanged();
                        fragment.getTotalCountandPrice(getCount(),getPrice());

                    }

                }
            });

            fragment.getTotalCountandPrice(getCount(),getPrice());


        }

    }


    @NonNull
    @Override
    public Adapter_Cart.newViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart,parent,false);
        height=parent.getContext().getResources().getDisplayMetrics().heightPixels;
        return new newViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_Cart.newViewHolder holder, int position) {

        holder.itemView.setTag(carts.get(position));
        holder.iwdisp.setImageResource(carts.get(position).getIwl());
        holder.title.setText(carts.get(position).getTitle());
        holder.desc.setText(carts.get(position).getDesc());
        holder.price.setText(carts.get(position).getPrice());
        holder.quantity.setText(carts.get(position).getQuantity());


        }



    @Override
    public int getItemCount() {
        return carts.size();
    }

    private String getCount(){
     int totalcount=0;
        for(Class_Cart temp:carts)
        {
            totalcount+=Integer.parseInt(temp.getQuantity().trim());

        }
        return Integer.toString(totalcount);
    }
    private String getPrice(){
        int totalprice=0;
        for(Class_Cart temp:carts)
        {

            int count=Integer.parseInt(temp.getQuantity().trim());
            totalprice+=Integer.parseInt(temp.getPrice().trim().substring(3))*count;

        }
        return Integer.toString(totalprice);
    }


}
