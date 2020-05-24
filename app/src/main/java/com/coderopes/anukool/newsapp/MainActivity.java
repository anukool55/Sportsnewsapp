package com.coderopes.anukool.newsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Locale;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {
    static TextView t1, t2, t3;
    static ImageView audiobutton;
    private InterstitialAd mInterstitialAd;
    static RecyclerView r1;
    jsonbackground jb;
CardView c1;
private Context c=this;
String newstype="";
public static ProgressBar pb1;


  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//ads
                        mInterstitialAd.show();

                  r1.setVisibility(View.INVISIBLE);
                   jb = new jsonbackground(c,"https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=5677a23adeef4382bdae1591aa099165");
                    jb.execute();
                    r1.setVisibility(View.VISIBLE);

                    return true;
                case R.id.navigation_dashboard:
                   //ads
                    mInterstitialAd.show();
                    r1.setVisibility(View.INVISIBLE);
                 jb = new jsonbackground(c,"https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=5677a23adeef4382bdae1591aa099165");
                    jb.execute();
                    r1.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    //ads
                    mInterstitialAd.show();
                    r1.setVisibility(View.INVISIBLE);
              jb = new jsonbackground(c,"https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=5677a23adeef4382bdae1591aa099165");
                    jb.execute();
                    r1.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3442937757617186/8190511327");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

/*Intent i=new Intent(this,adsAct.class);
startActivity(i);
finish();*/


 pb1=(ProgressBar)findViewById(R.id.pb1);
        ConnectivityManager conmgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = conmgr.getActiveNetworkInfo();
        if (netinfo == null || !netinfo.isConnected() || !netinfo.isAvailable()) {

            try {


                AlertDialog ald = new AlertDialog.Builder(this).create();
                ald.setTitle("Info");
                ald.setMessage("Internet not available,Turn on mobile data OR wifi,Please restart app ");
                //ald.setIcon(R.drawable.ic_dialo_alert);
                ald.setIcon(R.drawable.ic_notifications_black_24dp);
                ald.show();
            }catch(Exception e)
            {

            }

        }



         else {
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//calling for fetching and parsing json data
            jb = new jsonbackground(this, "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=5677a23adeef4382bdae1591aa099165");
     jb.execute();


//this is for recyclerview
            r1 = (RecyclerView) findViewById(R.id.r1);
            r1.setLayoutManager(new LinearLayoutManager(this));
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        jb.adpt.textToSpeech.stop();
    }



}







