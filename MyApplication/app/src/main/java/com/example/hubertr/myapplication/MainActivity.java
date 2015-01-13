package com.example.hubertr.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button isVisible = (Button) findViewById(R.id.button2);
        isVisible.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onPanButtonClick(View view) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getString(R.string.ButtonClicked));
        Button hiddenButton = (Button) findViewById(R.id.button2);




        RadioButton rButton = (RadioButton) findViewById(R.id.radioButton);

        if (rButton.isChecked()) {
            hiddenButton.setText("Jestem !");
        }
        else
            hiddenButton.setText("Nie ma mnie");




    }

    public void onRadioButtonClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton:
                if (checked) {
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText("Radio Button Checked !");
                    Button isVisible = (Button) findViewById(R.id.button2);
                    isVisible.setVisibility(View.VISIBLE);
                    break;
                }

        }
    }


    public void onHiddenButtonClick(View view) {
        RadioButton rButton = (RadioButton) findViewById(R.id.radioButton);
        rButton.setChecked(false);

        Button hiddenButton = (Button) findViewById(R.id.button2);
        hiddenButton.setVisibility(View.GONE);


    }
}
