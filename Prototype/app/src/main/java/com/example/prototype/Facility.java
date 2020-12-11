package com.example.prototype;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class Facility extends AppCompatActivity {

    private BottomNavigationView facilityNav;
    private FrameLayout facilityFrame;

    private FacilityOne facilityOne;
    private FacilityTwo facilityTwo;
    private FacilityThree facilityThree;
    private FacilityFour facilityFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);

        facilityFrame = (FrameLayout)findViewById(R.id.facility_frame);
        facilityNav = (BottomNavigationView) findViewById(R.id.facility_nav);

        facilityOne = new FacilityOne();
        facilityTwo = new FacilityTwo();
        facilityThree = new FacilityThree();
        facilityFour = new FacilityFour();

        setFragment(facilityOne);

        facilityNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_facility_event :
                        setFragment(facilityOne);
                        return true;
                    case R.id.menu_facility_planner :
                        setFragment(facilityTwo);
                        return true;
                    case R.id.menu_facility_person :
                        setFragment(facilityThree);
                        return true;
                    case R.id.menu_facility_building :
                        setFragment(facilityFour);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.facility_frame,fragment);
        fragmentTransaction.commit();
    }
}
