package com.tollywood2bollywood.t2bliveapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tollywood2bollywood.t2bliveapp.Other.SessionManager;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigation;
    FrameLayout mFrame;

    private AboutFragment aboutFragment;
    private ContactFragment contactFragment;
    private HomeFragment homeFragment;
    SessionManager sessionManager;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(MainActivity.this);
        //Inflating toolbar in MainActivity
        mToolbar = (Toolbar) findViewById(R.id.main_page_appbar);
        setSupportActionBar(mToolbar);


        mBottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigator);
        mFrame = (FrameLayout)findViewById(R.id.main_frame);

        //Creating constructor
        homeFragment = new HomeFragment();
        setFragment(homeFragment);


        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_menu_home) {
                    homeFragment = new HomeFragment();
                    setFragment(homeFragment);
                } else if (id == R.id.bottom_menu_about) {
                    aboutFragment = new AboutFragment();
                    setFragment(aboutFragment);
                }  else if (id == R.id.bottom_menu_contact) {
                    contactFragment  = new ContactFragment();
                    setFragment(contactFragment);
                }
                return true;
            }
        });

    }



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            sessionManager.delet();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
