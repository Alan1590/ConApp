package com.conapp.alangon.conapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.conapp.alangon.basedatos.TrabajoBaseDatosLogeoUsuario;

public class ActividadPrincipal extends AppCompatActivity {
    ImageView imgViewMisFacturas, imgViewMisPagos, imgViewChatStaff, imgViewNotificaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        imgViewMisFacturas = (ImageView) findViewById(R.id.imageViewPrincipalMisFacturas);
        imgViewMisFacturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verMisFacturas();
            }
        });
    }

    private void verMisFacturas(){
        Intent intMisFacturas = new Intent(this, InvoiceActivity.class);
        startActivity(intMisFacturas);
    }
}
