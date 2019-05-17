package com.example.anukool.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class jsonbackground extends AsyncTask<Void,Void,Void> {
    URL baseurl;
    String data="";
    String text;

public String[] tittle;
public String[] decription;
    public String[] img;
JSONArray ja;
Context context;

    jsonbackground(Context context)
    {
        this.context=context;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {

            baseurl=new URL("https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=5677a23adeef4382bdae1591aa099165");
            //opening connection with url
            HttpsURLConnection connection=(HttpsURLConnection)baseurl.openConnection();
            //setting up input stream
            InputStream is=connection.getInputStream();
            //setting uo bufferreader to read inoutstream data
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
String line="";
while(line!=null)
{//read data line by line
    line=br.readLine();
    data=data+line;
}
//creating a JSON object
JSONObject jo=new JSONObject(data);
 ja=jo.getJSONArray("articles");
//tittle array is used for the tittle of the news
tittle=new String[ja.length()];
for(int i=0;i<ja.length();i++)
{


    JSONObject o=ja.getJSONObject(i);
text=o.getString("title");

tittle[i]=text;

}
//description is used for description of the news
            decription=new String[ja.length()];
            for(int i=0;i<ja.length();i++)
            {


                JSONObject o=ja.getJSONObject(i);
                text=o.getString("description");

                decription[i]=text;

            }
            //this is used for url of the image
            img=new String[ja.length()];
            for(int i=0;i<ja.length();i++)
            {


                JSONObject o=ja.getJSONObject(i);
                text=o.getString("urlToImage");

                img[i]=text;

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
           e.printStackTrace();
        }

        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);



//calling the constructer of adapter class
        MainActivity.r1.setAdapter(new adapterclass(tittle, decription,img,context));



    }
}
