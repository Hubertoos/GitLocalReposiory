package com.example.hubertr.simplelistview;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity1 extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    List<ImageHolder> imgHolder;
    private HandleXML obj;
    private String urlRSS = "http://www.nasa.gov/rss/image_of_the_day.rss";
    List<ImageHolder> lista;
    public static final String intentURL = "URL do obrazu";
    public static final String intentTitle = "Tytuł obrazu";
    public static final String intentDate = "data obrazu";
    public static final String intentDesc = "Opis obrazu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_activity1);
        listView = (ListView) findViewById(R.id.sportsList);


        obj = new HandleXML(urlRSS);
        obj.fetchXML();
        while (obj.parsingComplete) ;
        lista = obj.getImageHolders();


        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, lista);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        /*Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + lista.get(position).getImageDescription(),
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();*/
        Intent showPicture = new Intent(ListViewActivity1.this, NasaDailyImage.class);

        showPicture.putExtra(intentDate, lista.get(position).getImageDate());
        showPicture.putExtra(intentTitle, lista.get(position).getImageTitle());
        showPicture.putExtra(intentDesc, lista.get(position).getImageDescription().toString());
        //showPicture.putExtra(intentDesc, "Opis");
        showPicture.putExtra(intentURL, lista.get(position).getImageUrl());

        startActivity(showPicture);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
