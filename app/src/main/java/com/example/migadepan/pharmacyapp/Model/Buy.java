package com.example.migadepan.pharmacyapp.Model;

/**
 * Created by migadepan on 24/01/2018.
 */

public class Buy {
    private int id;
    private int idPharm;
    private int idCli;
    private String date;

    public Buy(int id, int idPharm, int idCli, String date) {
        this.id = id;
        this.idPharm = idPharm;
        this.idCli = idCli;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPharm() {
        return idPharm;
    }

    public void setIdPharm(int idPharm) {
        this.idPharm = idPharm;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
