package com.example.migadepan.pharmacyapp.Controller;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.migadepan.pharmacyapp.Adapters.ProductAdapter;
import com.example.migadepan.pharmacyapp.DataBase.DatabaseHelper;
import com.example.migadepan.pharmacyapp.IntroMapsActivity;
import com.example.migadepan.pharmacyapp.Model.JsonPlaceHolderAPI;
import com.example.migadepan.pharmacyapp.Model.Store;
import com.example.migadepan.pharmacyapp.Model.Product;
import com.example.migadepan.pharmacyapp.R;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatalogActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
    ListView listProducts;
    Button btn_cesta;
    Store storeSelected;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> productsBuy = new ArrayList<>();
    int idPharm;
    int idUser;
    ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        idPharm = getIntent().getExtras().getInt("storeSelected");
        idUser = getIntent().getExtras().getInt("idUser");
        DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
        storeSelected = dbh.getStore(idPharm);

        getSupportActionBar().setTitle(storeSelected.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Elementos
        listProducts = (ListView) findViewById(R.id.list_products);
        btn_cesta = (Button) findViewById(R.id.button_carrito);

        //Obtener los productos
        fetchCatalogue();

        //Uso el adaptador
        ProductAdapter productAdapter = new ProductAdapter(this,products,productsBuy,btn_cesta);
        listProducts.setAdapter(productAdapter);

        btn_cesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, CestaActivity.class);
                intent.putParcelableArrayListExtra("itemsBuy", productsBuy);
                intent.putExtra("pharmSelected",idPharm);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, IntroMapsActivity.class);
                startActivity(intent);
                return true;
            case R.id.opc_search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(this);
                searchView.setQueryHint("Buscar...");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            if(products!=null && products.size()>0){
                ProductAdapter adapter = new ProductAdapter(this,products,productsBuy,btn_cesta);
                listProducts.setAdapter(adapter);
            }else{
                //Mostrar que no hay compañias almacenadas
            }
            return false;
        }

        ArrayList<Product> filteredValues = (ArrayList<Product>) products.clone();

        Iterator<Product> iter = filteredValues.iterator();

        while (iter.hasNext()) {
            Product d = iter.next();
            if (!d.getName().toLowerCase().contains(newText.toLowerCase())) {
                iter.remove();
            }
        }

        mAdapter = new ProductAdapter(this, filteredValues,productsBuy,btn_cesta);
        listProducts.setAdapter(mAdapter);

        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return false;
    }


    private void fetchCatalogue() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("locahost:8080/store/rest/store/1/catalogue")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<ArrayList<Product>> call = jsonPlaceHolderAPI.getProductList();
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (!response.isSuccessful()) { return; }
                ArrayList<Product> productList = response.body();
                for(int i=0; i<productList.size(); i++){
                    Product p = productList.get(i);
                    products.add(p);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }
}
