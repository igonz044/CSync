package com.example.csync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
    }

    public void onCalendarClick(View v) {
        fileBuilder file = new fileBuilder("");
        Intent intent = file.export();
        startActivity(intent);
    }
}
