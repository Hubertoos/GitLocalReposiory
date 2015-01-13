package com.example.xmlparser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String url2 = "&mode=xml";
    private EditText location,country,temperature,humidity,pressure;
    private TextView xml_text;
    private HandleXML obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location = (EditText)findViewById(R.id.editText1);
        country = (EditText)findViewById(R.id.editText2);
        temperature = (EditText)findViewById(R.id.editText3);
        humidity = (EditText)findViewById(R.id.editText4);
        pressure = (EditText)findViewById(R.id.editText5);
        xml_text = (TextView)findViewById(R.id.textViewXML);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void open(View view){
        String url = location.getText().toString();
        String finalUrl = url1 + url + url2;
        country.setText(finalUrl);
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);

        if (obj.parserException){
            location.setText("");
            country.setText("");
            temperature.setText("");
            humidity.setText("");
            pressure.setText("");
            xml_text.setText("");
            Toast.makeText(this, "Wybierz coś normalnego !!", Toast.LENGTH_LONG).show();
        }
        else if (obj.fetchException){
            location.setText("");
            country.setText("");
            temperature.setText("");
            humidity.setText("");
            pressure.setText("");
            xml_text.setText("");
            Toast.makeText(this, "Brak połączenia z siecią !!", Toast.LENGTH_LONG).show();
        }
        else {
            country.setText(obj.getCountry());
            temperature.setText(obj.getTemperature());
            humidity.setText(obj.getHumidity());
            pressure.setText(obj.getPressure());
            xml_text.setText(obj.getXML_text());
        }

    }

    public void onButtonWarszawaClick(View view) {
        Button butt = (Button) findViewById(R.id.buttonWarszawa);
        String url = (String) butt.getText();
        String finalUrl = url1 + url + url2;
        country.setText(finalUrl);
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);

        if (obj.parserException){
            location.setText("");
            country.setText("");
            temperature.setText("");
            humidity.setText("");
            pressure.setText("");
            xml_text.setText("");
            Toast.makeText(this, "Wybierz coś normalnego !!", Toast.LENGTH_LONG).show();
        }
        else if (obj.fetchException){
            location.setText("");
            country.setText("");
            temperature.setText("");
            humidity.setText("");
            pressure.setText("");
            xml_text.setText("");
            Toast.makeText(this, "Brak połączenia z siecią !!", Toast.LENGTH_LONG).show();
        }
        else {
            location.setText(url);
            country.setText(obj.getCountry());
            temperature.setText(obj.getTemperature());
            humidity.setText(obj.getHumidity());
            pressure.setText(obj.getPressure());
            xml_text.setText(obj.getXML_text());
        }

    }
}