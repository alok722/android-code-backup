package com.example.imalok.bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button)findViewById(R.id.button);
        t = (TextView)findViewById(R.id.text);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(i,2);
            }
        });
    }
    protected  void onActivityResult(int reqC,int resC,Intent data){
        super.onActivityResult(reqC,resC,data);

        if (reqC==2){
            String msg = data.getStringExtra("MESS");
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        }
    }
}
