package com.example.anukool.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity {
static TextView t1,t2,t3;


    static RecyclerView   r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
t1=(TextView)findViewById(R.id.t1);
        t3=(TextView)findViewById(R.id.t3);
        t2=(TextView)findViewById(R.id.t2);
//calling for fetching and parsing json data
       jsonbackground jb=new jsonbackground(this);
      jb.execute();


//this is for recyclerview
        r1=(RecyclerView)findViewById(R.id.r1);
  r1.setLayoutManager(new LinearLayoutManager(this));


    }
}
