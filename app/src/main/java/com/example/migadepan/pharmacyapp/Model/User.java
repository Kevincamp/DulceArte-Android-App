package com.example.migadepan.pharmacyapp.Model;

/**
 * Created by migadepan on 23/01/2018.
 */

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    private static User userConected;

//    public static User getUser (int id, String name, String surname, String birth, String dni, String mail, String pass){
//        if (userConected==null) {
//            userConected=new User(id, name, surname, birth, dni, mail, pass);
//        }
//        return userConected;
//    }

    public static User getInstancia(){return userConected;}

    public User(int id,
                String name,
                String mail,
                String pass) {
        this.id = id;
        this.name = name;
        this.email = mail;
        this.password = pass;
    }

    public User(String name, String surname, String birth, String dni, String mail, String pass) {
        this.name = name;
        this.email = mail;
        this.password = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String pass) {
        this.password = pass;
    }
}
