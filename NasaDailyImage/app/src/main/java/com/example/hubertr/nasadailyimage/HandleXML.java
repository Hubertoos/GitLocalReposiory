package com.example.hubertr.nasadailyimage;

import android.graphics.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;


public class HandleXML {

    private String urlString = null;
    private String pictureUrl = null;
    private Bitmap image = null;
    private String title = null;
    private StringBuffer description = new StringBuffer();
    private String date = null;

    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parserException = false;
    public volatile boolean fetchException = false;
    public volatile boolean parsingComplete = true;
    public HandleXML(String url){
        this.urlString = url;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }
    public String getPictureUrl() { return pictureUrl; }
    public StringBuffer getDescription() { return description; }





    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;

        //parsingComplete = false;


        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("title")){
                            if (title==null)
                            title = text;
                        }
                        else if(name.equals("description")){
                            description.append(text);
                        }
                        else if(name.equals("enclosure")){
                            if (pictureUrl==null)
                            pictureUrl = myParser.getAttributeValue(null,"url");
                        }
                        else if(name.equals("pubDate")){
                            if (date==null)
                            date = text;
                        }
                        else if(name.equals("source")){
                            parsingComplete = false;
                        }
                        else{
                        }
                        break;
                }
                if (parsingComplete)
                event = myParser.next();
                else {
                    event = XmlPullParser.END_DOCUMENT;
                }

            }
          parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
            parserException = true;
            parsingComplete = false;
        }

    }
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)
                            url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                            , false);
                    myparser.setInput(stream, null);
                    parseXMLAndStoreIt(myparser);
                    stream.close();


                } catch (Exception e) {
                    e.printStackTrace();
                    fetchException = true;
                    parsingComplete = false;
                }
            }
        });

        thread.start();


    }



}
