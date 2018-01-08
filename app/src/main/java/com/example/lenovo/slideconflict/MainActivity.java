package com.example.lenovo.slideconflict;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.slide_conflict);
        button.setOnClickListener(this);

        Button button1 = (Button) findViewById(R.id.to_outeside);
        button1.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.to_innerside);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.slide_conflict:
                Intent intent = new Intent(this, SlideConflictActivity.class);
                startActivity(intent);
                break;
            case R.id.to_outeside:
                Intent intent1 = new Intent(this, OuterSideInterceptActivity.class);
                startActivity(intent1);
                break;
            case R.id.to_innerside:
                Intent intent2 = new Intent(this, InterSideInterceptActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
