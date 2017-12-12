package com.conapp.alangon.personalizaciones;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.conapp.alangon.conapp.R;

/**
 * Created by Alan Gon on 12/12/2017.
 */

public class VistaListaPersonalizada extends BaseAdapter {
    Context ctx;
    ArrayAdapter<String> odooInvoices;
    private static LayoutInflater inflate;
    String[] testeo;
    public VistaListaPersonalizada(Context ctx, String[] testeo) {
        this.ctx = ctx;
        this.testeo = testeo;
        inflate = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return testeo.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // inflate the layout for each list row
        view = inflate.inflate(R.layout.vista_lista_personalizada, null);
        // get the TextView for item name and item description
        TextView textViewNombreFactura = (TextView)
                view.findViewById(R.id.textViewNombreFacturaListaPersonalizada);
        TextView textViewFechaFactura = (TextView)
                view.findViewById(R.id.textViewFechaListaPersonalizada);
        TextView textViewMontoFactura = (TextView)
                view.findViewById(R.id.textViewMontoListaPersonalizada);
        textViewFechaFactura.setText("22/15/17");
        textViewNombreFactura.setText("Factura 0005-00000034/FVA");
        textViewMontoFactura.setText("1500000");

        //sets the text for item name and item description from the current item object
//        textViewItemName.setText(currentItem.getItemName());
//        textViewItemDescription.setText(currentItem.getItemDescription());

        // returns the view for the current row
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
