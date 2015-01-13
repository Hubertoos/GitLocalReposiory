package com.example.xmlparser;


        import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.*;


public class HandleXML {

    private String country = "county";
    private String temperature = "temperature";
    private String humidity = "humidity";
    private String pressure = "pressure";
    private String xml_text = "xml_text";

    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parserException = false;
    public volatile boolean fetchException = false;
    public volatile boolean parsingComplete = true;
    public HandleXML(String url){
        this.urlString = url;
    }
    public String getCountry(){
        return country;
    }
    public String getTemperature(){
        return temperature;
    }
    public String getHumidity(){
        return humidity;
    }
    public String getPressure(){
        return pressure;
    }
    public String getXML_text(){
        return xml_text;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        xml_text = "Jestem w parserze";
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
                        if(name.equals("country")){
                            country = text;
                        }
                        else if(name.equals("humidity")){
                            humidity = myParser.getAttributeValue(null,"value");
                        }
                        else if(name.equals("pressure")){
                            pressure = myParser.getAttributeValue(null,"value");
                        }
                        else if(name.equals("temperature")){
                            temperature = myParser.getAttributeValue(null,"value");
                        }
                        else if(name.equals("weather")){
                            xml_text +=  '\n' + "Pogoda: " + myParser.getAttributeValue(null,"value");
                        }
                        else if(name.equals("clouds")){
                            xml_text += '\n' + "Zachmurzenie: " +  myParser.getAttributeValue(null, "name");
                        }
                        else if(name.equals("lastupdate")){
                            xml_text += '\n' +  myParser.getAttributeValue(null, "value");
                        }
                        else{
                        }
                        break;
                }
                event = myParser.next();

            }
            Double degree = Double.valueOf(temperature);
            double wynik = degree - 273.15;

            DecimalFormat df = new DecimalFormat("#.##");
            //df.format(wynik);
            temperature = String.valueOf(df.format(wynik));


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
                    xml_text = String.valueOf(stream);

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
