package com.example.imalok.griddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridView romance_grid;
    GridView crime_grid;

    String letterlist[]={"Romance","Romance","Romance","Romance","Romance","Romance","Romance","Romance","Romance"};
    String letterlist1[]={"Crime","Crime","Crime","Crime","Crime","Crime","Crime","Crime","Crime"};

    int letterIcon[]={R.drawable.romantic1,
            R.drawable.romantic1,
            R.drawable.romantic1,
            R.drawable.romantic1,
            R.drawable.romantic1,
            R.drawable.romantic1,
            R.drawable.romantic1,
            R.drawable.romantic1,
            R.drawable.romantic1,
            };

    int letterIcon1[]={R.drawable.crime,
                   R.drawable.crime,
                   R.drawable.crime,
                   R.drawable.crime,
                   R.drawable.crime,
                   R.drawable.crime,
                   R.drawable.crime,
                   R.drawable.crime,
                   R.drawable.crime};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        romance_grid = (GridView)findViewById(R.id.romance_grid);

        GridAdapter adapter = new GridAdapter(MainActivity.this,letterIcon,letterlist);

        romance_grid.setAdapter(adapter);

        romance_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Clicked Letter :" +letterlist[position],Toast.LENGTH_SHORT).show();
            }
        });

        crime_grid = (GridView)findViewById(R.id.crime_grid);

        GridAdapter adapter1 = new GridAdapter(MainActivity.this,letterIcon1,letterlist1);

        crime_grid.setAdapter(adapter1);

        crime_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Clicked Letter :" +letterlist1[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
