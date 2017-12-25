package com.conapp.alangon.conapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.conapp.alangon.basedatos.modelo.OdooBaseDatosInvoices;
import com.conapp.alangon.personalizaciones.FiltrosParaOdoo;
import com.conapp.alangon.personalizaciones.VistaListaPersonalizada;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class InvoiceActivity extends AppCompatActivity {
    /*************LISTA VARIABLES******************/
    ListView listView;
    TextView txt_view;
    private ArrayList<String> listaFacturas = new ArrayList<>();
    /*************LISTA VARIABLES******************/

    public void agregarResultados(String result){
        listaFacturas.add(result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        listView = (ListView) findViewById(R.id.listViewInvoices);

        //VistaListaPersonalizada listaFacturasOdoo = new VistaListaPersonalizada(this,test);
        //listView.setAdapter(listaFacturasOdoo);
    }

}
