package com.example.myinterview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final String INPUT_DATA_KEY = "MainActivity.INPUT_DATA_KEY";
    private EditText companyNameRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companyNameRef = findViewById(R.id.Company_input);
    }



    public void goForSearchActivity(View view) {
        String companyName = companyNameRef.getText().toString();

        Intent firstIntent = new Intent(MainActivity.this,SearchCompanyActivity.class);
        firstIntent.putExtra(INPUT_DATA_KEY,companyName);
        MainActivity.this.startActivity(firstIntent);
    }
}