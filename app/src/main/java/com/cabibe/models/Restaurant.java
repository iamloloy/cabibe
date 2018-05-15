package com.cabibe.models;

import java.io.Serializable;

/**
 * Created by ekxia on 5/2/2018.
 */

public class Restaurant implements Serializable {

    public int id;
    public String name;
    public String address;
    public double longitude;
    public double latitude;
    public String schedule;

    public Restaurant(int id, String name, String address, double latitude, double longitude, String schedule) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.schedule = schedule;
    }
}
