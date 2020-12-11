package com.example.imalok.imagepicker;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//public class MainActivity extends AppCompatActivity {

//  @Override
//protected void onCreate(Bundle savedInstanceState) {
//  super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_main);
//}
//}
//import android.support.v7.app.AppCompatActivity;
// import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText wish;
    Button submit,imagebutton;
    ImageView imageview;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wish = (EditText)findViewById(R.id.wish);
        submit = (Button)findViewById(R.id.submit);
        imageview = (ImageView)findViewById(R.id.imageview);
        imagebutton = (Button)findViewById(R.id.imagebutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                Bundle bundle=new Bundle();
                imageview.buildDrawingCache();
                Bitmap image= imageview.getDrawingCache();

                Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", image);
                //i.putExtras(extras);
                //startActivity(i);          //R.drawable.imageview);
                //i.putExtras(bundle);
                //startActivity(i);
                i.putExtra("wish",wish.getText().toString());
                i.putExtras(extras);
                startActivity(i);
            }
        });
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }
    public void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // startActivity(gallery);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if ((resultCode==RESULT_OK) && (requestCode==PICK_IMAGE)){
            imageUri = data.getData();
            imageview.setImageURI(imageUri);
        }
    }

}
