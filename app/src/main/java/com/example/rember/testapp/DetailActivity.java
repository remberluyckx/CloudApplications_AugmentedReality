package com.example.rember.testapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    Intent i;
    TextView viewQ;
    TextView viewA1;
    TextView viewA2;
    TextView viewA3;
    TextView viewA4;
    TextView viewA5;
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

        db = new DBHandler(this);
        Cursor resultSet = db.getReadableDatabase().rawQuery("Select * from Questions",null);

        if (resultSet.moveToPosition(selected)) {
                String question = resultSet.getString(1);
                viewQ.setText(question);
                String answer1 = resultSet.getString(2);
                viewA1.setText(answer1);
                String answer2 = resultSet.getString(3);
                viewA2.setText(answer2);
                String answer3 = resultSet.getString(4);
                viewA3.setText(answer3);
                String answer4 = resultSet.getString(5);
                viewA4.setText(answer4);
                String answer5 = resultSet.getString(6);
                viewA5.setText(answer5);
        }
    }
}
