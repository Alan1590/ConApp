package com.conapp.alangon.conapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.conapp.alangon.Odoo.XmlRpcOdoo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buton_logeo = (Button) findViewById(R.id.buttonMain_aceptar);
        buton_logeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              logeo("Usuario","Contraseña");
            }

        });
    }

    protected void logeo(String user, String password){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("HOLA");
        builder.setTitle("HAFSFASF");
        builder.show();
        builder.create();

    }

    public void registrarNuevoUsuario(View v){


        Intent intNuevoUsuario = new Intent(this,Nuevo_usuarioActivity.class);
        startActivity(intNuevoUsuario);
    }
    }
