package com.conapp.alangon.conapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.conapp.alangon.basedatos.TrabajoBaseDatos;

public class nuevo_usuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        TrabajoBaseDatos baseDatos = new TrabajoBaseDatos();
        baseDatos.TrabajoBaseDatos();
    }

}
