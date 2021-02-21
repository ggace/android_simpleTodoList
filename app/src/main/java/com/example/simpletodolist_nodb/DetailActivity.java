package com.example.simpletodolist_nodb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        ((TextView)findViewById(R.id.id)).setText(intent.getIntExtra("id", 0) + "");
        ((TextView)findViewById(R.id.content)).setText(intent.getStringExtra("content").toString());
    }
}