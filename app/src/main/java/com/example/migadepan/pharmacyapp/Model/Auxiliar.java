package com.example.migadepan.pharmacyapp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.migadepan.pharmacyapp.R;

/**
 * Created by migadepan on 24/01/2018.
 */

public class Auxiliar {
    public static void avisoToast(Context context, LayoutInflater inflater, ViewGroup idLayout, String texto){
        Toast toast = new Toast(context);
        View layout = inflater.inflate(R.layout.toast_layout, idLayout);

        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(texto);

        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
