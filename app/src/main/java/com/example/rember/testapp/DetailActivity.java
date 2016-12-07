package com.example.rember.testapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    Intent i;
    TextView viewQ;
    TextView viewA1;
    TextView viewA2;
    TextView viewA3;
    TextView viewA4;
    TextView viewA5;
    Button scan;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        i = getIntent();
        int selected = (int)i.getSerializableExtra("selectedQuestion");
        viewQ = (TextView) findViewById(R.id.questionView);
        viewA1 = (TextView) findViewById(R.id.answer1View);
        viewA2 = (TextView) findViewById(R.id.answer2View);
        viewA3 = (TextView) findViewById(R.id.answer3View);
        viewA4 = (TextView) findViewById(R.id.answer4View);
        viewA5 = (TextView) findViewById(R.id.answer5View);
        scan = (Button) findViewById(R.id.btnScan);
        db = new DBHandler(this);
        Cursor resultSet = db.getReadableDatabase().rawQuery("Select * from Questions",null);

        if (resultSet.moveToPosition(selected)) {
                String question = resultSet.getString(1);
                viewQ.setText(question);
                String answer1 = resultSet.getString(2);
                viewA1.setText("answer 1: " + answer1);
                String answer2 = resultSet.getString(3);
                viewA2.setText("answer 2: " + answer2);
                String answer3 = resultSet.getString(4);
                viewA3.setText("answer 3: " + answer3);
                String answer4 = resultSet.getString(5);
                viewA4.setText("answer 4: " + answer4);
                String answer5 = resultSet.getString(6);
                viewA5.setText("answer 5: " + answer5);
        }

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
