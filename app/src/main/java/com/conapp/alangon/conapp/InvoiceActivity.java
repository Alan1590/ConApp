package com.conapp.alangon.conapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.conapp.alangon.basedatos.modelo.OdooBaseDatosInvoices;
import com.conapp.alangon.personalizaciones.ClaseDialogos;
import com.conapp.alangon.personalizaciones.FiltrosParaOdoo;
import com.conapp.alangon.personalizaciones.VistaListaPersonalizada;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class InvoiceActivity extends AppCompatActivity {
    /*************LISTA VARIABLES******************/
    ListView listView;
    TextView txt_view;
    OdooBaseDatosInvoices invoicesOdoo;
    VistaListaPersonalizada listaFacturasOdoo;
    Object[] listaFacturas = null;
    ClaseDialogos dialogos;

    private static VistaListaPersonalizada listaPersonalizada;

    public static void setListaPersonalizada(VistaListaPersonalizada listaPersonalizada) {
        InvoiceActivity.listaPersonalizada = listaPersonalizada;
    }

    /*************LISTA VARIABLES******************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        listView = (ListView) findViewById(R.id.listViewInvoices);

        FiltrosParaOdoo filtrosOdoo = new FiltrosParaOdoo(1);
        filtrosOdoo.addFiltro(0,"number","like","");
        filtrosOdoo.setCampos(2);
        invoicesOdoo = new OdooBaseDatosInvoices(this,
                "http://192.168.0.244:8069/xmlrpc/2/object",
                "pruebasOdoo2","admin","admin",1);
        invoicesOdoo.selectModel(2);
        invoicesOdoo.setMapeoCampos(filtrosOdoo.getMapeoCampos());
        invoicesOdoo.setFiltros(filtrosOdoo.getFiltro());
        invoicesOdoo.setListView(listView);
        invoicesOdoo.execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                verDetalles(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void verDetalles(int idFactura){
        Intent intVerFactura = new Intent(this,ActivityVerFacturas.class);
        ActivityVerFacturas.setIdFactura(idFactura);
        startActivity(intVerFactura);

    }
}
