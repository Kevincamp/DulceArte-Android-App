package com.example.migadepan.pharmacyapp.Controller;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.migadepan.pharmacyapp.DataBase.DatabaseHelper;
import com.example.migadepan.pharmacyapp.Model.Auxiliar;
import com.example.migadepan.pharmacyapp.Model.JsonPlaceHolderAPI;
import com.example.migadepan.pharmacyapp.Model.Store;
import com.example.migadepan.pharmacyapp.Model.Product;
import com.example.migadepan.pharmacyapp.Model.User;
import com.example.migadepan.pharmacyapp.Network.ApiUtils;
import com.example.migadepan.pharmacyapp.R;
import com.example.migadepan.pharmacyapp.manager.APIService;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    private APIService apiService;

    private EditText userTxt;
    private EditText passwordTxt;
    private Button loginBtn;


    User userConected = null;
    DatabaseHelper bdh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Esconder la barra superior de info
        getSupportActionBar().hide();

        apiService = ApiUtils.getAPIService();

        //Obtener elementos
        userTxt = (EditText) findViewById(R.id.et_user_login);
        passwordTxt = (EditText) findViewById(R.id.et_pass_login);
        loginBtn = (Button) findViewById(R.id.btn_login);

        //Loguearse
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();
            }
        });
    }


    //TODO: Not here
//        bdh = new DatabaseHelper(getApplicationContext());
//        if(bdh != null) {
//            //Para renovar la db
//            SQLiteDatabase sqldb = bdh.getWritableDatabase();
//            bdh.onUpgrade(sqldb,1,2);
//        }

    private void authenticate() {
        String email = userTxt.getText().toString().replace(" ","");
        String password = passwordTxt.getText().toString();
        try {
            JsonObject paramObject = new JsonObject();
            paramObject.addProperty("email", email);
            paramObject.addProperty("password", password);

            apiService.login(paramObject).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.d("SUCCESS CALL ","success");
                    if (!response.isSuccessful()){
                        Log.e("********* ERROR",response.errorBody().toString());
                        Auxiliar.avisoToast(getApplicationContext(),getLayoutInflater(),(ViewGroup) findViewById(R.id.idtoast),"Usuario o contraseña incorrectos");
                        return;
                    }

                    User u = response.body();
                    DatabaseHelper bdh = new DatabaseHelper(getApplicationContext());
                    int userId = bdh.saveUserDB(u);
                    fetchStoresInfo(userId);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("********* ERROR",t.getMessage());
                    Auxiliar.avisoToast(getApplicationContext(),getLayoutInflater(),(ViewGroup) findViewById(R.id.idtoast),"Usuario o contraseña incorrectos");
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void fetchStoresInfo(int userId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("locahost:8080/store/rest/stores")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<ArrayList<Store>> call = jsonPlaceHolderAPI.getStoreList();
        call.enqueue(new Callback<ArrayList<Store>>() {
            @Override
            public void onResponse(Call<ArrayList<Store>> call, Response<ArrayList<Store>> response) {
                if (!response.isSuccessful()){
                    return;
                }

                ArrayList<Store> storeList = response.body();
                DatabaseHelper bdh = new DatabaseHelper(getApplicationContext());
                for(int i=0; i < storeList.size(); i++) {
                    Store s = storeList.get(i);
                    bdh.addStore(s);
                }

                Store pSelected = bdh.getStore(1);
                Intent intent = new Intent(LoginActivity.this, CatalogActivity.class);
                intent.putExtra("storeSelected", pSelected.getId());
                intent.putExtra("idUser",userConected.getId());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ArrayList<Store>> call, Throwable t) {

            }
        });
    }


    private void legacySetup() {
//        SQLiteDatabase db = bdh.getWritableDatabase();
//        User uKevin = new User("Kevin","Campuzano","20/04/1990","75167162b","kevincamp@correo.ugr.es","123");
//        User uCristina = new User("Cristina","Vaillant","07/11/1992","71453879d","ccvaillant1992@gmail.com","123");
//        int idUser = bdh.addUser(uKevin);
//        int idUser2 = bdh.addUser(uCristina);
//        System.out.println("INSERTADO USER ID " + idUser);
//        User u1 = bdh.getUser(1);
//
//        DatabaseHelper bdh = new DatabaseHelper(getApplicationContext());
//        Store p1 = new Store("Fybeca el milagro","37.205602","-3.611477","Calle Joaquina Eguaras, 23, 18013 Granada");
//        Store p2 = new Store("Fybeca francia","37.208538","-3.605795","Calle Pintor Gabriel Morcillo, 18013 Granada");
//        Store p3 = new Store("Fybeca Ecuador","37.201535","-3.601538","Calle Luz Casanova, 6, 18011 Granada");
//        Store p4 = new Store("Fybeca Ayanz","37.196869","-3.608898","Av. de Juan Pablo II, 68, 18013 Granada");
//
//        bdh.addStore(p1);
//        bdh.addStore(p2);
//        bdh.addStore(p3);
//        bdh.addStore(p4);
//
//        //Productos String name, int cant, float precio, String department
//        Product pr1 = new Product("Bufanda",100,(float)5.50, "Accesorios");
//        Product pr2 = new Product("Gafas",100,(float)2, "Accesorios");
//        Product pr3 = new Product("Cartera",50,(float)10, "Accesorios");
//        Product pr4 = new Product("Blusa blanca",100,(float)4.50, "Vestimenta");
//        Product pr5 = new Product("Gorra",20,(float)8.50,"Accesorios");
//        Product pr6 = new Product("Jean",10,(float)15.50,"Vestimenta");
//        Product pr7 = new Product("Camisa",10,(float)10, "Vestimenta");
//        Product pr8 = new Product("Cadena de oro",100,(float)17, "Accesorios");
//        Product pr9 = new Product("Fosfomicina",100,(float)11, "Medicamentos");
//        Product pr10 = new Product("Rosas perfume",50,(float)30, "Perfumería");
//        Product pr11 = new Product("Nivea sun",10,(float)45.50, "Parafarmacia");
//        Product pr12 = new Product("Garnier limpiador",10,(float)15, "Parafarmacia");
//        Product pr13 = new Product("Aromatic perfume",30,(float)22, "Perfumería");
//        Product pr14 = new Product("Loreal hidratante facial",20,(float)14.50, "Parafarmacia");
//
//        bdh.addProduct(pr1);
//        bdh.addProduct(pr2);
//        bdh.addProduct(pr3);
//        bdh.addProduct(pr4);
//        bdh.addProduct(pr5);
//        bdh.addProduct(pr6);
//        bdh.addProduct(pr7);
//        bdh.addProduct(pr8);
//        bdh.addProduct(pr9);
//        bdh.addProduct(pr10);
//        bdh.addProduct(pr11);
//        bdh.addProduct(pr12);
//        bdh.addProduct(pr13);
//        bdh.addProduct(pr14);
//
//        bdh.addProductCatalog(1,1);
//        bdh.addProductCatalog(1,2);
//        bdh.addProductCatalog(1,3);
//        bdh.addProductCatalog(1,4);
//        bdh.addProductCatalog(1,5);
//        bdh.addProductCatalog(1,6);
//        bdh.addProductCatalog(1,7);
//        bdh.addProductCatalog(1,8);
//        bdh.addProductCatalog(1,9);
//        bdh.addProductCatalog(1,10);
//        bdh.addProductCatalog(2,1);
//        bdh.addProductCatalog(2,2);
//        bdh.addProductCatalog(2,3);
//        bdh.addProductCatalog(2,4);
//        bdh.addProductCatalog(2,7);
//        bdh.addProductCatalog(2,9);
//        bdh.addProductCatalog(2,11);
//        bdh.addProductCatalog(2,13);
//        bdh.addProductCatalog(2,14);
//        bdh.addProductCatalog(2,10);
//        bdh.addProductCatalog(3,1);
//        bdh.addProductCatalog(3,2);
//        bdh.addProductCatalog(3,3);
//        bdh.addProductCatalog(3,4);
//        bdh.addProductCatalog(3,5);
//        bdh.addProductCatalog(4,6);
//        bdh.addProductCatalog(4,7);
//        bdh.addProductCatalog(4,8);
//        bdh.addProductCatalog(4,9);
//        bdh.addProductCatalog(4,10);
//
//        Store p = bdh.getStore(1);
//        System.out.println(p.getName());
    }
}
