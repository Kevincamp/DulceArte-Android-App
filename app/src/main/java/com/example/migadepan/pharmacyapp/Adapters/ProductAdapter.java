package com.example.migadepan.pharmacyapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.migadepan.pharmacyapp.DataBase.DatabaseHelper;
import com.example.migadepan.pharmacyapp.Model.Product;
import com.example.migadepan.pharmacyapp.R;

import java.util.ArrayList;

/**
 * Created by migadepan on 29/09/2017.
 */

public class ProductAdapter extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Product> items;
    protected ArrayList<Product> itemsBuy;
    Button btn_carrito;

    public ProductAdapter(Activity activity, ArrayList<Product> items, ArrayList<Product> itemsBuy, Button btnCarrito) {
        this.activity = activity;
        this.items = items;
        this.itemsBuy = itemsBuy;
        this.btn_carrito = btnCarrito;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_product, null);
        }

        ImageView image_prod = (ImageView) v.findViewById(R.id.imageView_product);
        TextView txt_name_prod = (TextView) v.findViewById(R.id.txt_name_product);
        TextView txt_precio_prod = (TextView) v.findViewById(R.id.txt_precio);
        Button btn_add = (Button) v.findViewById(R.id.btn_add_prod);

        int resource = 0;
        if(items.get(i).getName().equals("Aspirina")){
            resource = R.drawable.aspirina ;
        }else if(items.get(i).getName().equals("Ibuprofeno")){
            resource = R.drawable.ibuprofeno;
        }else if(items.get(i).getName().equals("Fosfomicina")){
            resource = R.drawable.fosfomicina;
        }else if(items.get(i).getName().equals("Paracetamol")){
            resource = R.drawable.paracetamol;
        }else{
            resource = R.drawable.sin_imagen;
        }


        final Product p = items.get(i);
        //Especialidad fecha y hora
        image_prod.setImageResource(resource);
        txt_name_prod.setText(items.get(i).getName());
        txt_precio_prod.setText(String.valueOf(items.get(i).getPrecio()).replace(".",",")+" €");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setSelected(true);
                p.setCant(p.getCant()+1);
                itemsBuy.add(p);

                btn_carrito.setText("VER CESTA ("+itemsBuy.size()+" items)");
                for (int i=0; i<itemsBuy.size();i++){
                    System.out.println(itemsBuy.get(i).getName());
                }
            }
        });

        return v;
    }
}