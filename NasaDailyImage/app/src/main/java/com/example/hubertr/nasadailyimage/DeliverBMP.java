package com.example.hubertr.nasadailyimage;

import android.graphics.*;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DeliverBMP {
    private String pictureUrl = null;
    private Bitmap image = null;
    public volatile boolean fetchException = false;

    public DeliverBMP(String url){
        this.pictureUrl = url;
    }

    //getters



    public Bitmap fetchBMP(){

                try {
                    //URL url = new URL(pictureUrl);
                    //HttpURLConnection conn = (HttpURLConnection)
                           // url.openConnection();
                    //conn.setReadTimeout(10000 /* milliseconds */);
                    //conn.setConnectTimeout(15000 /* milliseconds */);
                    //conn.setRequestMethod("GET");
                    HttpURLConnection connection =
                            (HttpURLConnection)new URL(pictureUrl).openConnection();
                    connection.setDoInput(true);
                    connection.connect();

                    //conn.setDoInput(true);
                    //conn.connect();
                    InputStream stream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(stream);
                    stream.close();
                    return bitmap;


                } catch (Exception e) {
                    e.printStackTrace();
                    fetchException = true;

                }
        {
            return null;
        }

    }
}
