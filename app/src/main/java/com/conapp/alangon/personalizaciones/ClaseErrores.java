package com.conapp.alangon.personalizaciones;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by AlanMSI on 15/11/2017.
 */

public class ClaseErrores extends AppCompatActivity {
    private Context ctx;

    public ClaseErrores(Context ctx) {
        this.ctx = ctx;
    }

    public void mensajeError(String titulo, String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(titulo);
        builder.setMessage(error).setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create().show();
    }

}
