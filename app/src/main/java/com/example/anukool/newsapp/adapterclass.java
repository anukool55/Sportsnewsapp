package com.example.anukool.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.anukool.newsapp.MainActivity.t1;

public class adapterclass extends RecyclerView.Adapter<adapterclass.pviewholder> {

private String[] data,data2,data3;
//data is used to represent tittle
    //data2 is used to represent description
    //data3 is used to represent the image url
Context context;
    public adapterclass(String[] data,String[] data2,String[] data3,Context context) {
        this.data=data;
        this.data2=data2;
        this.data3=data3;
        this.context=context;
    }

    @NonNull
    @Override
    public pviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inf= LayoutInflater.from(parent.getContext());
        View view=inf.inflate(R.layout.recyclelayout,parent,false);
        return new pviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pviewholder holder, int position) {

        //her tittle is taken
        String tittle=data[position];
holder.t1.setText(tittle);

//her description is taken
String description=data2[position];
        holder.t2.setText(description);

//her image url is taken
        String img=data3[position];
       Glide.with(context).load(img).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class pviewholder extends RecyclerView.ViewHolder{
ImageView img1;
TextView t1,t2,t3;

        public pviewholder(View itemView) {
            super(itemView);
          img1=(ImageView)itemView.findViewById(R.id.img1);
            t1=(TextView)itemView.findViewById(R.id.t1);
            t2=(TextView)itemView.findViewById(R.id.t2);
            t3=(TextView)itemView.findViewById(R.id.t3);
        }
    }

}
