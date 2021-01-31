package com.example.migadepan.pharmacyapp.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.migadepan.pharmacyapp.Adapters.PayAdapter;
import com.example.migadepan.pharmacyapp.DataBase.DatabaseHelper;
import com.example.migadepan.pharmacyapp.Model.DialogoAlerta;
import com.example.migadepan.pharmacyapp.Model.Product;
import com.example.migadepan.pharmacyapp.Model.User;
import com.example.migadepan.pharmacyapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PayActivity extends AppCompatActivity {
    ArrayList<Product> comprados;
    int idPharm;
    ListView list;
    TextView total;
    float totalpay = 0;
    Button btn_pay;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        getSupportActionBar().setTitle("Pagar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        comprados = getIntent().getExtras().getParcelableArrayList("itemsBuy");
        idPharm = getIntent().getExtras().getInt("pharmSelected");
        idUser = getIntent().getExtras().getInt("idUser");

        list = (ListView) findViewById(R.id.list_pay);
        total = (TextView) findViewById(R.id.txt_total_price);
        btn_pay = (Button) findViewById(R.id.button_pay);


        PayAdapter payAdapter = new PayAdapter(this,comprados);
        list.setAdapter(payAdapter);


        for (int i=0;i<comprados.size();i++){
            totalpay += comprados.get(i).getPrecio();
        }
        total.setText(String.valueOf(totalpay)+" €");

        ///Fecha
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Realizar el pedido
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = df.format(c.getTime());

                DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
                int compra = dbh.addBuy(idPharm,idUser,formattedDate);
                for (int i=0;i<comprados.size();i++){
                    dbh.addProductBuy(compra,comprados.get(i).getId(),1);
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager,"tag");

            }
        });


    }
}
