package com.example.migadepan.pharmacyapp.Model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.migadepan.pharmacyapp.Controller.LoginActivity;
import com.example.migadepan.pharmacyapp.IntroMapsActivity;

/**
 * Created by migadepan on 25/01/2018.
 */

public class DialogoAlerta extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("El pedido se ha realizado correctamente. Se cerrará la sesión para su seguridad.")
                .setTitle("Pedido completado")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent activity = new Intent(getActivity(), LoginActivity.class);
                        startActivity(activity);
                    }
                });

        return builder.create();
    }
}
