package com.example.rember.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        i = getIntent();
       // final String selectedPlayer = i.getSerializableExtra("selectedPlayer");

       // TextView tvNaam = (TextView) findViewById(R.id.txtName);
        //tvNaam.setText(selectedPlayer.getFirstName() + " " + selectedPlayer.getLastName());
    }
}
