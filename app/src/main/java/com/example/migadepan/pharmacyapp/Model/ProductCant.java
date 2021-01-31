package com.example.migadepan.pharmacyapp.Model;

/**
 * Created by migadepan on 24/01/2018.
 */

public class ProductCant {
    private int idBuy;
    private int idProduct;
    private int cant;

    public ProductCant(int idBuy, int idProduct, int cant) {
        this.idBuy = idBuy;
        this.idProduct = idProduct;
        this.cant = cant;
    }

    public int getIdBuy() {
        return idBuy;
    }

    public void setIdBuy(int idBuy) {
        this.idBuy = idBuy;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
