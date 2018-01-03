package com.conapp.alangon.personalizaciones;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by AlanMSI on 15/11/2017.
 */

public class ClaseDialogos {
    private Context ctx;
    private ProgressDialog dialogoProgreso;
    private boolean hayError;
    public ClaseDialogos(Context ctx) {
        this.ctx = ctx;
        dialogoProgreso = new ProgressDialog(ctx);

    }

    public void progresoDialogo(String titulo, String mensaje){

        dialogoProgreso.setTitle(titulo);
        dialogoProgreso.setMessage(mensaje);
        dialogoProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

    }

    public void progresoDialogo(String titulo, String mensaje, int progreso, int maximo){

        dialogoProgreso.setTitle(titulo);
        dialogoProgreso.setMessage(mensaje);
        dialogoProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialogoProgreso.setMax(maximo);
        dialogoProgreso.setProgress(progreso);
    }

    public void cerrarProgresoDialogo(){dialogoProgreso.dismiss();}
    public void mostrarProgresoDialogo(){dialogoProgreso.show();}

    public void mensajeError(String titulo, String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(titulo);
        builder.setMessage(error).setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hayError = true;
            }
        }).create().show();
    }


}
