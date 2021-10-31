package com.example.myinterview;

import static com.example.myinterview.MainActivity.INPUT_DATA_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.net.URLEncoder;

public class SearchCompanyActivity extends AppCompatActivity {

    private String companyFromFirst;


    private EditText hour;
    private EditText minutes;
    private EditText subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_company);

        subject = findViewById(R.id.alarm_title_text);
        hour = findViewById(R.id.alarm_hout_text);
        minutes = findViewById(R.id.alarm_min_text);


        Intent intentFromMain = getIntent(); // get the intent that initiated the activity (only 1 intent at a time)
        this.companyFromFirst = intentFromMain.getStringExtra(INPUT_DATA_KEY);
    }

    public void googleSearch(View view) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, companyFromFirst); // query contains search string
        startActivity(intent);
    }

    public void mapSearch(View view) {
        Uri locationUri = Uri.parse("geo:0,0?q="+this.companyFromFirst);
        // create an implicit intent that prompts android to open using one the installed apps
        // on the device. Pass what action is needed and which data to work on
        Intent intent = new Intent(Intent.ACTION_VIEW,locationUri);
        // check if there is a compatible app (activity) that can present the data above
        // getPackageManager - Returns PackageManager instance to find global package information.
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else{
            // if there is no compatible app print to logcat
            Log.d("geoLocationOpenError", "There is no app to open a google maps API location");
        }

    }

    public void goToSetActivity(View view) {
        Intent firstIntent = new Intent(this,SetInterviewActivity.class);
        firstIntent.putExtra(INPUT_DATA_KEY,companyFromFirst);
        this.startActivity(firstIntent);
    }

    public void setAlarm(View view){
        String alarmTitle = subject.getText().toString();
        String alarmHour = hour.getText().toString();
        String alarmMin = minutes.getText().toString();
        int alarm_hour = Integer.parseInt(alarmHour);
        int alarm_min = Integer.parseInt(alarmMin);
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, alarmTitle)
                .putExtra(AlarmClock.EXTRA_HOUR, alarm_hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, alarm_min);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            Log.d("geoLocationOpenError", "There is no app to open a google maps API location");

        }
    }
}