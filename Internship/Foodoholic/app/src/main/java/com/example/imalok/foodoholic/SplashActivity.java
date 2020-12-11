package com.example.imalok.foodoholic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {

    private GifImageView gifImageView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       getSupportActionBar().hide();

       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gifImageView=(GifImageView)findViewById(R.id.splash_loader);
        imageView=(ImageView)findViewById(R.id.splash_logo);

        Animation logo_anim = AnimationUtils.loadAnimation(this,R.anim.splash_logo);
        Animation loader_anim = AnimationUtils.loadAnimation(this,R.anim.splash_loader);

        gifImageView.startAnimation(loader_anim);
        imageView.startAnimation(logo_anim);

        final Intent i = new Intent(this,GetStarted.class);
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
