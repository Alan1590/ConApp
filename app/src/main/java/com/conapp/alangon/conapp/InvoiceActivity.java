package com.conapp.alangon.conapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InvoiceActivity extends AppCompatActivity {
    ListView listViewInvoices;
    TextView txt_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        listViewInvoices = (ListView) findViewById(R.id.listViewInvoices);
        ArrayAdapter<String> list = new ArrayAdapter<String>(this,R.layout.vista_lista_personalizada);
        list.add("HOLA");
        list.add("Como");
        list.add("HOLA");
        list.add("HOLA");
        list.add("HOLA");

        listViewInvoices.setAdapter(list);
    }
}
