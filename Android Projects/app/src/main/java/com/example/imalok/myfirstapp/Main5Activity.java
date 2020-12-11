package com.example.imalok.myfirstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.view.View;

import java.net.URI;

public class Main5Activity extends AppCompatActivity {
    Button clk;
    Button alok;
    VideoView videov;
    MediaController mediaC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        clk = (Button)findViewById(R.id.button4);
        videov = (VideoView)findViewById(R.id.video);
        mediaC = new MediaController(this);
        alok = (Button)findViewById(R.id.exit);
        alok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main5Activity.this);

                // set title
                alertDialogBuilder.setTitle("Love");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do You Want To STOP This Video?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog,int id)
                                    {
                                        // if this button is clicked, close
                                        // current activity
                                        Main5Activity.this.finish();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog,int id)
                                    {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }
    public void videoplay(View view){
        String videopath = "android.resource://"+getPackageName() +"/" +R.raw.aaa;
        Uri uri = Uri.parse(videopath);
        videov.setVideoURI(uri);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);
        videov.start();
    }
}
