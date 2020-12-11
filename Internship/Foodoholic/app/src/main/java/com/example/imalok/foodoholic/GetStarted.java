package com.example.imalok.foodoholic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GetStarted extends AppCompatActivity {

    private ViewPager viewPager;
    private FirstSliderAdapter firstSliderAdapter;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    Button regbtn,loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        //setting the logo in actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo12);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //mapping button
        regbtn=(Button)findViewById(R.id.reg);
        loginbtn=(Button)findViewById(R.id.login);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GetStarted.this,Login.class);
                startActivity(i);
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GetStarted.this,Register.class);
                startActivity(i);
            }
        });

        //Custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/junction_light.ttf");
        regbtn.setTypeface(custom_font);
        loginbtn.setTypeface(custom_font);

        viewPager = (ViewPager)findViewById(R.id.first_slider);
        firstSliderAdapter = new FirstSliderAdapter(this);
        viewPager.setAdapter(firstSliderAdapter);

        //Auto-sliding of Viewpager Image
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 5) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    //Creating Option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Handling the menu of Option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==R.id.about)
        {
            Intent i = new Intent(this,About.class);
            startActivity(i);
        }
        if (item.getItemId()==R.id.feedback)
        {
            Intent i = new Intent(this,Feedback.class);
            startActivity(i);
        }
        if (item.getItemId()==R.id.exit)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
