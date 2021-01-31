package com.example.migadepan.pharmacyapp.Model;

/**
 * Created by migadepan on 23/01/2018.
 */

public class Store {
    private int id;
    private String code;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    public static Store storeSelected;

    public static Store getPharmacy (int id, String name, String latitude, String longitude){
        if (storeSelected ==null) {
            storeSelected =new Store(id, name, latitude, longitude);
        }
        return storeSelected;
    }

    public static Store getInstancia(){return storeSelected;}

    public Store(int id, String name, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Store(String name, String latitude, String longitude, String address) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public Store(int id, String name, String latitude, String longitude, String address) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
