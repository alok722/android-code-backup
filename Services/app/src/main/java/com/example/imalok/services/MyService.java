package com.example.imalok.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    MediaPlayer mplayer;
    public void onCreate() {
        mplayer = MediaPlayer.create(this,R.raw.holdmyhand);
        mplayer.setLooping(false);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void onStart(Intent i,int id){
        int a = in.getS
        mplayer.start();
    }
    public void onDestroy(){
        mplayer.stop();
    }
}
