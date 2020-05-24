package com.coderopes.anukool.newsapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Locale;

import static android.media.CamcorderProfile.get;
import static com.coderopes.anukool.newsapp.MainActivity.audiobutton;
import static com.coderopes.anukool.newsapp.MainActivity.r1;
import static com.coderopes.anukool.newsapp.MainActivity.t1;

public class adapterclass extends RecyclerView.Adapter<adapterclass.pviewholder> {

public String[] data,data2,data3,data4;
    String tittle,description,link,a;

    public TextToSpeech textToSpeech;
//data is used to represent tittle
    //data2 is used to represent description
    //data3 is used to represent the image url
Context context;
    public adapterclass(String[] data,String[] data2,String[] data3,String[] data4,Context context) {
        this.data=data;
        this.data2=data2;
        this.data3=data3;
        this.data4=data4;
        this.context=context;
    }

    @NonNull
    @Override
    public pviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inf= LayoutInflater.from(parent.getContext());
        View view=inf.inflate(R.layout.recyclelayout,parent,false);
        return new pviewholder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull pviewholder holder, final int position) {

        //her tittle is taken
         tittle=data[position];
holder.t1.setText(tittle );

//here description is taken
description=data2[position];
        holder.t2.setText(description);

//here image url is taken
        String img=data3[position];
       Glide.with(context).load(img).into(holder.img1);

        int stat=position;

        //link is taken here
        link=data4[position];
holder.t4.setText("click : "+link);
//audio data
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                }
            }
        });

        holder.audiobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


    int speechStatus = textToSpeech.speak(data[position] + data2[position], TextToSpeech.QUEUE_FLUSH, null);

    if (speechStatus == TextToSpeech.ERROR) {
        Log.e("TTS", "Error in converting Text to Speech!");
    }
}


        });
//on scroll listner to stop audio when user scroll up or down
        r1.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                textToSpeech.stop();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class pviewholder extends RecyclerView.ViewHolder{
ImageView img1;
TextView t1,t2,t4;
        public ImageView audiobutton;
        public pviewholder(View itemView) {
            super(itemView);
          img1=(ImageView)itemView.findViewById(R.id.img1);
            t1=(TextView)itemView.findViewById(R.id.t1);
            t2=(TextView)itemView.findViewById(R.id.t2);

            t4=(TextView)itemView.findViewById(R.id.lin);
audiobutton=(ImageView)itemView.findViewById(R.id.audiobutton);
        }




    }




}
