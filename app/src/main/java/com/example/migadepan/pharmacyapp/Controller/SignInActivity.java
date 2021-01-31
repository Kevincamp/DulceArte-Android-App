package com.example.migadepan.pharmacyapp.Controller;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.migadepan.pharmacyapp.DataBase.DatabaseHelper;
import com.example.migadepan.pharmacyapp.Model.Auxiliar;
import com.example.migadepan.pharmacyapp.Model.User;
import com.example.migadepan.pharmacyapp.R;

public class SignInActivity extends AppCompatActivity {
    EditText et_name;
    EditText et_surname;
    EditText et_dni;
    EditText et_birth;
    EditText et_mail;
    EditText et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Titulo del menu superior
        getSupportActionBar().setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        et_name = (EditText) findViewById(R.id.editText_name);
        et_surname = (EditText) findViewById(R.id.editText_surname);
        et_birth = (EditText) findViewById(R.id.editText_birth);
        et_dni = (EditText) findViewById(R.id.editText_dni);
        et_mail = (EditText) findViewById(R.id.editText_mail);
        et_pass = (EditText) findViewById(R.id.editText_pass);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.opc_cancel:
                //Desecho los datos y vuelvo a la actividad
                Intent actividad = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(actividad);
                return true;
            case R.id.opc_confirm:
                //Inserto usuario
                insertUser();
                return true;
        }
        return true;
    }


    public void insertUser(){
        if(!et_mail.getText().toString().isEmpty()&&!et_pass.getText().toString().isEmpty()&&!et_name.getText().toString().isEmpty()&&et_surname.getText().toString().isEmpty()&&!et_dni.getText().toString().isEmpty()&&et_birth.getText().toString().isEmpty()){
            User u = new User(et_name.getText().toString(),et_surname.getText().toString(),et_birth.getText().toString(),et_dni.getText().toString(),et_mail.getText().toString(),et_pass.getText().toString());
            DatabaseHelper bdh = new DatabaseHelper(getApplicationContext());
//            bdh.addUser(u);
            Intent actividad2 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(actividad2);
        }else{
            Auxiliar.avisoToast(getApplicationContext(),getLayoutInflater(),(ViewGroup) findViewById(R.id.idtoast),"Debe completar todos los campos");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }
}
