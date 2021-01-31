package com.example.migadepan.pharmacyapp.Controller;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.migadepan.pharmacyapp.Adapters.CestaAdapter;
import com.example.migadepan.pharmacyapp.Model.Product;
import com.example.migadepan.pharmacyapp.R;

import java.util.ArrayList;

public class CestaActivity extends AppCompatActivity {
    int idPharm;
    int idUser;

    ListView listProducts;
    Button btn_pay;
    ArrayList<Product> comprados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);

        getSupportActionBar().setTitle("Cesta de la compra");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        comprados = getIntent().getExtras().getParcelableArrayList("itemsBuy");
        idPharm = getIntent().getExtras().getInt("pharmSelected");
        idUser = getIntent().getExtras().getInt("idUser");

        //Elementos
        listProducts = (ListView) findViewById(R.id.list_products_buy);
        btn_pay = (Button) findViewById(R.id.button_pagar);

        CestaAdapter cestaAdapter = new CestaAdapter(this,comprados,idPharm);
        listProducts.setAdapter(cestaAdapter);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CestaActivity.this, PayActivity.class);
                intent.putParcelableArrayListExtra("itemsBuy", comprados);
                intent.putExtra("pharmSelected",idPharm);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, CatalogActivity.class);
                intent.putExtra("pharmSelected",idPharm);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
                return true;
        }
        return true;
    }
}
