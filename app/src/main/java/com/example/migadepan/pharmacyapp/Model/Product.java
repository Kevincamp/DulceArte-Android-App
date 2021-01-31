package com.example.migadepan.pharmacyapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by migadepan on 24/01/2018.
 */

public class Product implements Parcelable {
    private int id;
    private String name;
    private int cant=0;
    private float precio;
    private String department;
    private String foto;
    private boolean selected = false;

    public Product(int id, String name, int cant, float precio, String department) {
        this.id = id;
        this.name = name;
        this.cant = cant;
        this.precio = precio;
        this.department = department;
    }

    public Product(String name, int cant, float precio, String department) {
        this.name = name;
        this.cant = cant;
        this.precio = precio;
        this.department = department;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        cant = in.readInt();
        precio = in.readFloat();
        department = in.readString();
        foto = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(cant);
        parcel.writeFloat(precio);
        parcel.writeString(department);
        parcel.writeString(foto);
        parcel.writeByte((byte) (selected ? 1 : 0));
    }
}
