package com.example.ch133.weepdayapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.IllegalFormatException;

public class WeekDay2 extends AppCompatActivity {

    private TextView tv_out, date_out;
    private Integer day, month, year, century,  week_days_ends;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day2);

    }

    private void getInputValues(){
        EditText et_day = (EditText) findViewById(R.id.edittextday);
        EditText et_month = (EditText) findViewById(R.id.edittextmonth);
        EditText et_year = (EditText) findViewById(R.id.edittextyear);
        try{
            day = Integer.parseInt(et_day.getText().toString());
        }
        catch(IllegalFormatException e){
            day = 0;
        }
        try{
            month = Integer.parseInt(et_month.getText().toString());
        }
        catch(IllegalFormatException e){
            month = 0;
        }


        if (month == 1)
            month = 13;
        else if (month == 2)
            month = 14;


        try {
            s = et_year.getText().toString();
            year = Integer.parseInt(s.substring(2));
            century =  Integer.parseInt(s.substring(0, 2));
        }
        catch (Exception e){
            year = 0;
            century = 0;
        }


        et_day.setText(day.toString());
        et_month.setText(month.toString());
        et_year.setText(s);

    }
    private void computeDay(){
        Log.d("computeDay()", "s = " + s + ", year = " + Integer.toString(year) + ", " + "century = " + Integer.toString(century));
        week_days_ends = (day + (int)(26 *(month + 1)/10.0) + year + (int)(year/4.0) + (int)(century/4.0) + 5 * century ) % 7;
    }

    private void printDay(){
        String whatday;
        switch(week_days_ends){
            case 0:
                whatday = "Saturday";
                break;
            case 1:
                whatday = "Sunday";
                break;
            case 2:
                whatday = "Monday";
                break;
            case 3:
                whatday = "Tuesday";
                break;
            case 4:
                whatday = "Wednesday";
                break;
            case 5:
                whatday = "Thursday";
                break;
            case 6:
                whatday = "Friday";
                break;
            default:
                whatday = "Invalid";
        }
        Intent intent = new Intent(this, SubActiviy_01.class);
        intent.putExtra(null,whatday);
        startActivity(intent);

    }

    public void onClick_01(View v){
        getInputValues();
        computeDay();
        printDay();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_week_day2, menu);
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
