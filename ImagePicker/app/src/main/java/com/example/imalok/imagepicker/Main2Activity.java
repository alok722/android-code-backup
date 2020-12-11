package com.example.imalok.imagepicker;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView t;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t = (TextView)findViewById(R.id.textView);
        img=(ImageView)findViewById(R.id.imageView1);
        //img.buildDrawingCache();
        //Bitmap image= img.getDrawingCache();

        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

        img.setImageBitmap(bmp );
        t.setText(getIntent().getExtras().getString("wish"));

    }
}


