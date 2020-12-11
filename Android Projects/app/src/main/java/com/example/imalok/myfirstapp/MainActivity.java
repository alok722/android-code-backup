package com.example.imalok.myfirstapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button clk1,clk2;
    MediaPlayer mdx;

    public Button button;
    public void init(){
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alok = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(alok);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clk1 = (Button)findViewById(R.id.playid);
        clk2 = (Button)findViewById(R.id.pauseid);
        mdx = MediaPlayer.create(MainActivity.this,R.raw.let);
        init();
    }
    public void clkplay(View v){
        mdx.start();
    }

    public void clkpause(View v){
        mdx.pause();
    }
}
