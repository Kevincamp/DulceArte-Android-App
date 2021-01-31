package com.example.migadepan.pharmacyapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.migadepan.pharmacyapp.Model.Buy;
import com.example.migadepan.pharmacyapp.Model.Store;
import com.example.migadepan.pharmacyapp.Model.Product;
import com.example.migadepan.pharmacyapp.Model.ProductCant;
import com.example.migadepan.pharmacyapp.Model.User;

import java.util.ArrayList;

/**
 * Created by migadepan on 24/01/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DBPharmacies";
    private static final int DATABASE_VERSION = 1;

    //Sentencias SQL para crear las tablas
    String sqlCreateTableUser = "CREATE TABLE User (id INTEGER, name TEXT, email TEXT, password TEXT) ";
    String sqlCreateTablePharmacy = "CREATE TABLE Store (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, latitud TEXT, longitud TEXT, address TEXT) ";
    String sqlCreateTableProduct = "CREATE TABLE Product (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cant INTEGER, precio REAL, department TEXT) ";
    String sqlCreateTableCatalog = "CREATE TABLE Catalog (idPharmacy INTEGER, idProduct INTEGER) ";
    String sqlCreateTableBuy = "CREATE TABLE Buy (id INTEGER PRIMARY KEY AUTOINCREMENT, idUser INTEGER, idPharmacy INTEGER, day TEXT) ";
    String sqlCreateTableListBuy = "CREATE TABLE Listbuy (idBuy INTEGER, idProduct INTEGER, cant INTEGER) ";

    public DatabaseHelper(Context contexto) {
        super(contexto, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateTableUser);
        db.execSQL(sqlCreateTablePharmacy);
        db.execSQL(sqlCreateTableProduct);
        db.execSQL(sqlCreateTableCatalog);
        db.execSQL(sqlCreateTableBuy);
        db.execSQL(sqlCreateTableListBuy);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        System.out.println("ACTUALIZA DB");
        //Para actualizar la db
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL(sqlCreateTableUser);
    }


    //Save User
    public int saveUserDB(User u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", u.getId());
        values.put("name", u.getName());
        values.put("email", u.getMail());
        values.put("password", u.getPass());

        // Inserting Row
        long iduser = db.insert("User", null, values);
        db.close(); // Closing database connection
        return (int) iduser;
    }

    public int addStore(Store p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", p.getName());
        values.put("latitud", p.getLatitude());
        values.put("longitud", p.getLongitude());
        values.put("address", p.getAddress());

        // Inserting Row
        long idPharmacy = db.insert("Pharmacy", null, values);
        db.close(); // Closing database connection
        return (int) idPharmacy;
    }

    //Add Product
    public int addProduct(Product p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", p.getName());
        values.put("cant", p.getCant());
        values.put("precio", p.getPrecio());
        values.put("department", p.getDepartment());

        // Inserting Row
        long idProduct = db.insert("Product", null, values);
        db.close(); // Closing database connection
        return (int) idProduct;
    }

    //Insert catalog
    public void addProductCatalog(int pharm, int prod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idPharmacy", pharm);
        values.put("idProduct", prod);

        // Inserting Row
        db.insert("Catalog", null, values);
        db.close(); // Closing database connection
    }


    //Insert buy
    public int addBuy(int pharm, int cli, String day) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idUser", cli);
        values.put("idPharmacy", pharm);
        values.put("day", day);

        // Inserting Row
        long idBuy = db.insert("Buy", null, values);
        db.close(); // Closing database connection
        return (int) idBuy;
    }

    //Insert buylist
    public void addProductBuy(int idBuy, int idProduct, int cant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idBuy", idBuy);
        values.put("idProduct", idProduct);
        values.put("cant", cant);

        // Inserting Row
        db.insert("Listbuy", null, values);
        db.close(); // Closing database connection
    }

    //Get User
//    public User getUser(int id){
//        User u = null;
//        String selectQuery = "SELECT * FROM User WHERE id="+id;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                u = new User(id,cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(3),cursor.getString(5),cursor.getString(6));
//            } while (cursor.moveToNext());
//        }
//        db.close(); // Closing database connection
//        return u;
//    }

    //Get Pharmacies
    public ArrayList<Store> getStoreList(){
        ArrayList<Store> pharmacies = new ArrayList<>();
        String selectQuery = "SELECT * FROM Pharmacy";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Store p = new Store(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                pharmacies.add(p);
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        return pharmacies;
    }

    //Get Pharmacy
    public Store getStore(int id){
        Store p = null;
        String selectQuery = "SELECT * FROM Pharmacy WHERE id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                p = new Store(id,cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        return p;
    }

    //Get Product
    public Product getProduct(int id){
        Product p = null;
        String selectQuery = "SELECT * FROM Product WHERE id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                p = new Product(id,cursor.getString(1),cursor.getInt(2),cursor.getFloat(3),cursor.getString(4));
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        return p;
    }

    public ArrayList<Integer> getCatalog(int id){
        ArrayList<Integer> catalog = new ArrayList<>();
        String selectQuery = "SELECT * FROM Catalog WHERE idPharmacy="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                catalog.add(cursor.getInt(1));
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        return catalog;
    }

    //Get Compra
    public ArrayList<Buy> getBuys(int idCli){
        ArrayList<Buy> compras = new ArrayList<>();
        String selectQuery = "SELECT * FROM Buy WHERE idUser="+idCli;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Buy b = new Buy(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3));
                compras.add(b);
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        return compras;
    }

    //Get Compra
    public ArrayList<ProductCant> getDetailsBuy(int idCompra){
        ArrayList<ProductCant> articulos = new ArrayList<>();
        String selectQuery = "SELECT * FROM Listbuy WHERE idBuy="+idCompra;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductCant pc = new ProductCant(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
                articulos.add(pc);
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        return articulos;
    }

    //Get Compra
//    public User doLogin(String mail, String pass){
//        User u = null;
//        String selectQuery = "SELECT * FROM User";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                if(cursor.getString(5).equals(mail) && cursor.getString(6).equals(pass)){
//                    u = getUser(cursor.getInt(0));
//                }
//            } while (cursor.moveToNext());
//        }
//        db.close(); // Closing database connection
//        return u;
//    }

}
