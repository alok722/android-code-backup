package com.example.imalok.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //defining Object
    TextView signUp;
    ImageView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_main);

        //mapping
        signUp =(TextView)findViewById(R.id.signUp);
        next = (ImageView)findViewById(R.id.next);

        //adding onClick method to change the activity
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(it);




            }
        });
        //adding onClick method to change the activity
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(it);
            }
        });

    }
}
