package com.example.VyAppar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Search extends RecyclerView.Adapter<Adapter_Search.newViewHolder> implements Filterable {

    private ArrayList<Class_Search_Categories> cats=new ArrayList<>();
    private ArrayList<Class_Search_Categories> FULL_LIST=new ArrayList<>();
    int height;
    SearchInterface activity;

    public interface SearchInterface{
        void updatecart(int i);
    }

    public Adapter_Search(Context context, ArrayList<Class_Search_Categories> list){
        cats = list;
        FULL_LIST=list;
        activity=(SearchInterface) context;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<Class_Search_Categories> filteredlist= new ArrayList<Class_Search_Categories>();

            if(charSequence.toString().isEmpty())
            {filteredlist.addAll(FULL_LIST);}
            else
            {
                for(Class_Search_Categories temp: FULL_LIST)
                {
                    if(SearchAlgo(temp.getTitle().toLowerCase(),charSequence.toString()))
                    {
                        filteredlist.add(temp);
                    }
                }
            }
            filteredlist=SortAlgo(filteredlist,charSequence.toString());
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredlist;

            return filterResults;
        }


    @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            cats= (ArrayList<Class_Search_Categories>) filterResults.values;
            notifyDataSetChanged();
            Log.d("The Publish","Of results ");

        }
    };

    public class newViewHolder extends RecyclerView.ViewHolder {

        public ImageView iwdisp,btnadd;
        public TextView title,desc,misc,rating;


        public newViewHolder(@NonNull final View itemView) {
            super(itemView);

            WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
            layoutParams.height=height/5;
            itemView.setLayoutParams(layoutParams);
            iwdisp=itemView.findViewById(R.id.iw1);
            title=itemView.findViewById(R.id.tv1);
            desc=itemView.findViewById(R.id.twDesc);
            misc=itemView.findViewById(R.id.tvMisc);
            rating=itemView.findViewById(R.id.tvrating);
            btnadd=itemView.findViewById(R.id.btnadd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //CLICK TO SEND INTENT
                }
            });

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddToCart(itemView);
                    activity.updatecart(cats.indexOf(itemView.getTag()));
                }
            });



        }

    }


    @NonNull
    @Override
    public Adapter_Search.newViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search,parent,false);
        height=parent.getContext().getResources().getDisplayMetrics().heightPixels;
        return new newViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Search.newViewHolder holder, int position) {

        holder.itemView.setTag(cats.get(position));
        holder.iwdisp.setImageResource(cats.get(position).getIwDisp());

        holder.title.setText(cats.get(position).getTitle());
        holder.desc.setText(cats.get(position).getDesc());
        holder.misc.setText(cats.get(position).getMisc());
        holder.rating.setText(cats.get(position).getRating());



    }

    @Override
    public int getItemCount() {
        return cats.size();
    }




    private ArrayList<Class_Search_Categories> SortAlgo(ArrayList<Class_Search_Categories> example, String seq)
    {
        seq=seq.toLowerCase().trim();

        for(int i=0;i<example.size();i++)
        {
            for(int j=i+1;j<example.size();j++)
            {
                int[] var1=new int[seq.length()];
                int[] var2=new int[seq.length()];

                for(int l=0;l<seq.length();l++)
                {
                    var1[l]=example.get(i).getTitle().indexOf(seq.substring(l,l+1));
                    var2[l]=example.get(j).getTitle().indexOf(seq.substring(l,l+1));

                }
                if(variance(var1,seq.length())>variance(var2,seq.length()))
                {
                    Class_Search_Categories temp= example.get(i);
                    example.set(i,example.get(j));
                    example.set(j,temp);

                }

            }


        }
        return example;


    }

    private double variance(int a[], int n)
    {
        // Compute mean (average of elements)
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += a[i];
        double mean = (double)sum /
                (double)n;

        // Compute sum squared
        // differences with mean.
        double sqDiff = 0;
        for (int i = 0; i < n; i++)
            sqDiff += (a[i] - mean) *
                    (a[i] - mean);
        return sqDiff / n;
    }
    private boolean SearchAlgo(String example, String seq)

    {
        example = example.toLowerCase().trim();
        seq = seq.toLowerCase().trim();
        int i;
        if(seq.length()==1)
        {
            if(example.contains(seq))
                return true;
            else
                return false;
        }
        else
        {

            if (example.contains(seq.substring(0,1)))
            {
                return (SearchAlgo(example.substring(example.indexOf(seq.substring(0,1))), seq.substring(1)));
            }
            else
            {
                return (false);
            }
        }
    }

    private void AddToCart(View itemview){
        int i=cats.indexOf(itemview.getTag()),FLAG=0;

        //
        String title=cats.get(i).getTitle();
        String desc=cats.get(i).getDesc();
        int disp=cats.get(i).getIwDisp();
        //
        for(Class_Cart temp:APPLICATION_CLASS.cart)
        {
            if(temp.getTitle().equals(title))
            {  FLAG=1;
               APPLICATION_CLASS.cart.get(APPLICATION_CLASS.cart.indexOf(temp)).setQuantity(Integer.toString(Integer.parseInt(temp.getQuantity())+1));
            }

        }
        if(FLAG==0)
        {
            APPLICATION_CLASS.cart.add(new Class_Cart(title,desc,"Rs.690","1",disp));
        }
        if(APPLICATION_CLASS.TOTAL_QUANTITY==null)
        {
            APPLICATION_CLASS.TOTAL_QUANTITY="1";
        }
        else if (APPLICATION_CLASS.TOTAL_QUANTITY.isEmpty())
        {APPLICATION_CLASS.TOTAL_QUANTITY="1";

        }
        else{
            APPLICATION_CLASS.TOTAL_QUANTITY=Integer.toString(Integer.parseInt(APPLICATION_CLASS.TOTAL_QUANTITY.trim())+1);
        }
    }

}


