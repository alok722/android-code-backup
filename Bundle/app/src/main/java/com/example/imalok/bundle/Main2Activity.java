package com.example.imalok.bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button b2 = (Button)findViewById(R.id.button2);
        final EditText ed = (EditText)findViewById(R.id.editText);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent();
                String str = ed.getText().toString();
                i2.putExtra("MESS",str);
                setResult(2,i2);
                finish();
            }
        });
    }
}
