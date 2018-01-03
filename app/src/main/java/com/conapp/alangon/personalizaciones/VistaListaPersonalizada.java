package com.conapp.alangon.personalizaciones;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.conapp.alangon.conapp.ActivityVerFacturas;
import com.conapp.alangon.conapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alan Gon on 12/12/2017.
 */

public class VistaListaPersonalizada extends BaseAdapter{
    Context ctx;
    Object[] odooInvoices;
    private static LayoutInflater inflate;
    private ClaseDialogos dialogos;
    private HashMap<String,Object> mapeoResultado;
    public VistaListaPersonalizada(Context ctx, Object[] odooInvoices) {
        this.odooInvoices = odooInvoices;
        this.ctx = ctx;
        inflate = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        ClaseDialogos dialogos = new ClaseDialogos(ctx);
        mapeoResultado = new HashMap<>();
    }

    @Override
    public int getCount() {
        return odooInvoices.length;
    }

    @Override
    public Object getItem(int i) {

        return odooInvoices[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // inflate the layout for each list row

        view = inflate.inflate(R.layout.vista_lista_personalizada, null);
        // get the TextView for item name and item description
        TextView textViewNombreFactura = (TextView)
                view.findViewById(R.id.textViewNombreFacturaListaPersonalizada);
        TextView textViewIdFactura = (TextView)
                view.findViewById(R.id.textViewIdListaPersonalizada);
        TextView textViewFechaFactura = (TextView)
                view.findViewById(R.id.textViewFechaListaPersonalizada);
        TextView textViewMontoFactura = (TextView)
                view.findViewById(R.id.textViewMontoListaPersonalizada);
        TextView textViewEstado = (TextView)
                view.findViewById(R.id.textViewPagadoListaPersonalizada);

        mapeoResultado.putAll((HashMap<String,Object>) getItem(i));
        textViewIdFactura.setText(String.valueOf(i));
        textViewFechaFactura.setText(mapeoResultado.get("date_invoice").toString());
        textViewNombreFactura.setText(mapeoResultado.get("number").toString());
        textViewMontoFactura.setText(mapeoResultado.get("amount_total").toString());
        switch(mapeoResultado.get("state").toString()){
            case("draft"):
                textViewEstado.setText("Borrador");
                view.setBackgroundColor(Color.GRAY);
                break;
            case("open"):
                textViewEstado.setText("Abierto");
                break;
            case("cancelled"):
                textViewEstado.setText("Cancelada");
                view.setBackgroundColor(Color.GRAY);
                break;
            case("paid"):
                textViewEstado.setText("Pagado");
                view.setBackgroundColor(Color.GREEN);
                break;

        }

        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

}
