package com.example.csync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    String string1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void onConfirmClick(View v) {
        Intent vision = new Intent(EditActivity.this, ExportActivity.class);
        EditText editText1 = (EditText)findViewById(R.id.editText2);
        String total = editText1.getText().toString() + ";";
        EditText editText2 = (EditText)findViewById(R.id.editText);
        total = total + editText2.getText().toString() + ";";
        EditText editText3 = (EditText)findViewById(R.id.editText3);
        total = total + editText3.getText().toString() + ";";
        EditText editText4 = (EditText)findViewById(R.id.editText4);
        total = total + editText4.getText().toString() + ";";
        EditText editText5 = (EditText)findViewById(R.id.editText5);
        total = total + editText5.getText().toString();
        vision.putExtra("START_EXPORT_INTENT", total);
        startActivity(vision);
    }
}
