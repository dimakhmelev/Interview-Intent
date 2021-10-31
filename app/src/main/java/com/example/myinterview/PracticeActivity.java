package com.example.myinterview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;

public class PracticeActivity extends AppCompatActivity {
    private EditText second;
    private String subject="Tic Tac...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        second = findViewById(R.id.timer_sec_text);
    }


    public void setTimer(View view){
        String timerSubject = this.subject;
        String timerSeconds = second.getText().toString();
        int timer_seconds = Integer.parseInt(timerSeconds);
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, timerSubject)
                .putExtra(AlarmClock.EXTRA_LENGTH, timer_seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}