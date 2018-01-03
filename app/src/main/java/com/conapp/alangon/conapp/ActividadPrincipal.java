package com.conapp.alangon.conapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.conapp.alangon.basedatos.TrabajoBaseDatosLogeoUsuario;
import com.conapp.alangon.basedatos.modelo.HashMapDatosConapp;
import com.conapp.alangon.basedatos.modelo.OdooBaseDatosInvoices;
import com.conapp.alangon.personalizaciones.FiltrosParaOdoo;
import com.conapp.alangon.personalizaciones.VistaListaPersonalizada;

import java.util.concurrent.ExecutionException;

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
