package com.example.hubertr.simplelistview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class NasaDailyImage extends ActionBarActivity {

    private HandleXML obj;
    private String urlRSS = "http://www.nasa.gov/rss/image_of_the_day.rss";
    private String urlString;
    private TextView date, title;
    private TextView description;
    private ImageView image;
    String intentURL = null;
    String intentDesc = null;
    String intentTitle = null;
    String intentDate = null;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_daily_image);

        intentURL = getIntent().getStringExtra(ListViewActivity1.intentURL);
        intentDesc = getIntent().getStringExtra(ListViewActivity1.intentDesc);
        intentTitle = getIntent().getStringExtra(ListViewActivity1.intentTitle);
        intentDate = getIntent().getStringExtra(ListViewActivity1.intentDate);





        date = (TextView) findViewById(R.id.imageDate);
        title = (TextView) findViewById(R.id.imageTitle);
        description = (TextView) findViewById(R.id.imageDescription);
        image = (ImageView) findViewById(R.id.imageDisplay);

        /*date.setText("DD/MM/YYYY");
        title.setText("TYTUŁ");
        description.setText("OPIS");
        image.setImageBitmap(null);*/


        title.setText(intentTitle);
        date.setText(intentDate);
        description.setText(intentDesc);

        // Create an object for subclass of AsyncTask
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[]{intentURL});

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("In progress...");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();

          }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nasa_menu_daily_image, menu);
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

    public void open(View view) {
        /*obj = new HandleXML(urlRSS);
        obj.fetchXML();
        while (obj.parsingComplete) ;
        urlString = obj.getPictureUrl();*/
        title.setText(intentTitle);
        date.setText(intentDate);
        description.setText(intentDesc);
        // Create an object for subclass of AsyncTask
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[]{intentURL});

    }

    public void onButtonResetClick(View view) {

        date.setText("DD/MM/YYYY");
        title.setText("TYTUŁ");
        description.setText("OPIS");
        image.setImageBitmap(null);
    }

    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {



        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }



        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {

            image.setImageBitmap(result);
            progressDialog.dismiss();
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream);//, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }

}







