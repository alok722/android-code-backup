package com.example.imalok.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    //defining Object
    TextView  signUp;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        //mapping
        back = (ImageView)findViewById(R.id.back);

        signUp = (TextView)findViewById(R.id.signUp);

        //adding onClick method
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(MainActivity2.this,MainActivity.class);
                startActivity(it);


            }
        });

        //adding onClick method
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(it);
            }
        });
    }
}
