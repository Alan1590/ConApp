package com.conapp.alangon.conapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.conapp.alangon.basedatos.modelo.HashMapOdooInvoices;
import com.conapp.alangon.basedatos.modelo.HashMapOdooPartner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityVerFacturas extends AppCompatActivity {
    private static int idFactura;

    public static int getIdFactura() {
        return idFactura;
    }

    public static void setIdFactura(int idFactura) {
        ActivityVerFacturas.idFactura = idFactura;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_facturas);
        TextView textViewNombreFactura = (TextView)
                findViewById(R.id.textViewNombreVerFactura);
        TextView textViewDescripcionFactura = (TextView)
                findViewById(R.id.textViewDescripcionVerFactura);
        TextView textViewFechaFactura = (TextView)
                findViewById(R.id.textViewFechaVerFactura);
        TextView textViewPartnerFactura = (TextView)
                findViewById(R.id.textViewPartnerVerFactura);
        TextView textViewMontoFactura = (TextView)
                findViewById(R.id.textViewMontoVerFactura);
        textViewNombreFactura.setText(HashMapOdooInvoices.getResultadoInvoices()[idFactura].get("number"));
        textViewFechaFactura.setText(HashMapOdooInvoices.getResultadoInvoices()[idFactura].get("date_invoice"));
        textViewDescripcionFactura.setText(String.valueOf(HashMapOdooInvoices.getResultadoInvoices()[idFactura].get("comment")));
        textViewMontoFactura.setText(String.valueOf(HashMapOdooInvoices.getResultadoInvoices()[idFactura].get("amount_total")));
        textViewPartnerFactura.setText(HashMapOdooPartner.getNombre());
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

}
