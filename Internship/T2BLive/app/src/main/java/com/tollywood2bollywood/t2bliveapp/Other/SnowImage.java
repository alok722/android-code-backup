package com.tollywood2bollywood.t2bliveapp.Other;



/**
 * Created by andy.barber on 13/02/2018.
 */
public class SnowImage implements ZigzagImage {

    private int snowImageResourceId;

    public SnowImage(int snowImageResourceId) {
        this.snowImageResourceId = snowImageResourceId;
    }

    @Override
    public String getZigzagImageUrl() {
        return null;
    }

    @Override
    public int getZigzagImageResourceId() {
        return snowImageResourceId;
    }
}
