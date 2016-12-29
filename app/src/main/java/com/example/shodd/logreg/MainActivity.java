package com.example.shodd.logreg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nameTV, userTV, ageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String username = intent.getStringExtra("username");

        nameTV = (TextView) findViewById(R.id.main_welcome_TV);
        userTV = (TextView) findViewById(R.id.main_username_TV);
        ageTV = (TextView) findViewById(R.id.main_age_TV);

        String message = "Welcome " + name;
        nameTV.setText(message);
        userTV.setText(username);
        ageTV.setText(age);
    }
}
