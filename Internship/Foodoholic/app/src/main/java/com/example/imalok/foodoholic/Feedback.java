package com.example.imalok.foodoholic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //setting the logo in actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo12);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}
