package com.example.csync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void onConfirmClick(View v) {
        Intent vision = new Intent(EditActivity.this, ExportActivity.class);
        startActivity(vision);
    }
}
