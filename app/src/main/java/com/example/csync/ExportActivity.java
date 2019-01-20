package com.example.csync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ExportActivity extends AppCompatActivity {

    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        info = intent.getStringExtra("START_EXPORT_INTENT");
        //System.out.println(info);

        /*String [] stuff = new String[5];
        stuff = info.split(";");

        EditText editText = (EditText)findViewById(R.id.editText7);
        editText.setText(stuff[0], TextView.BufferType.EDITABLE);

        EditText editText2 = (EditText)findViewById(R.id.editText6);
        editText2.setText(stuff[1], TextView.BufferType.EDITABLE);

        EditText editText3 = (EditText)findViewById(R.id.editText8);
        editText3.setText(stuff[2], TextView.BufferType.EDITABLE);

        EditText editText4 = (EditText)findViewById(R.id.editText9);
        editText4.setText(stuff[3], TextView.BufferType.EDITABLE);

        EditText editText5 = (EditText)findViewById(R.id.editText10);
        editText5.setText(stuff[4], TextView.BufferType.EDITABLE);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
    }

    public void onCalendarClick(View v) {
        fileBuilder file = new fileBuilder(info);
        Intent intent = file.export();
        startActivity(intent);
    }
}
