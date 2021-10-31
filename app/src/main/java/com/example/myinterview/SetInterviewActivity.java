package com.example.myinterview;

import static com.example.myinterview.MainActivity.INPUT_DATA_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetInterviewActivity extends AppCompatActivity {

    Person person=new Person();
    private String companyFromFirst;

    private EditText PersonNameRef;
    private EditText PersonMobileRef;
    private EditText companyEmailRef;

    private EditText EmailToSend;
    private EditText textToSend;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_interview);
        Intent intentFromMain = getIntent(); // get the intent that initiated the activity (only 1 intent at a time)
        this.companyFromFirst = intentFromMain.getStringExtra(INPUT_DATA_KEY);
        PersonNameRef = findViewById(R.id.Name_input);
        PersonMobileRef = findViewById(R.id.Mobile_input);
        companyEmailRef = findViewById(R.id.Email_input);
        EmailToSend = findViewById(R.id.Send_Email_input);
        textToSend = findViewById(R.id.Send_text_input);


    }

    public void addAsContact(View view) {
        person.setName(PersonNameRef.getText().toString());
        person.setMobile(PersonMobileRef.getText().toString());
        person.setEmail(companyEmailRef.getText().toString());

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        intent.putExtra(ContactsContract.Intents.Insert.NAME, person.getName());
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, person.getMobile());
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, person.getEmail());

        startActivity(intent);
    }

    public void sendEmail(View view) {
        String Email=EmailToSend.getText().toString();
        Intent intentMail = new Intent(Intent.ACTION_SEND);
        intentMail.setType("message/rfc822");
        intentMail.putExtra(Intent.EXTRA_EMAIL, Email); // the To mail.
        intentMail.putExtra(Intent.EXTRA_SUBJECT, "Interview");
        intentMail.putExtra(Intent.EXTRA_TEXT, textToSend.getText().toString());

// now we have created the mail, lets try and send it.
        try {
            startActivity(Intent.createChooser(intentMail, "Message to User to do what next"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void callNow(View view) {
        Intent dialIntent = new Intent();
        dialIntent.setAction(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:"+PersonMobileRef.getText().toString()));
        startActivity(dialIntent);

    }

    public void goToPracticeActivity(View view) {
        Intent firstIntent = new Intent(this,PracticeActivity.class);
        this.startActivity(firstIntent);

    }



}