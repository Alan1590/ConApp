package com.conapp.alangon.basedatos.modelo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.conapp.alangon.Odoo.axmlrpc.XMLRPCCallback;
import com.conapp.alangon.Odoo.axmlrpc.XMLRPCClient;
import com.conapp.alangon.Odoo.axmlrpc.XMLRPCException;
import com.conapp.alangon.conapp.InvoiceActivity;
import com.conapp.alangon.conapp.MainActivity;
import com.conapp.alangon.personalizaciones.ClaseDialogos;
import com.conapp.alangon.personalizaciones.FiltrosParaOdoo;
import com.conapp.alangon.personalizaciones.VistaListaPersonalizada;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alan Gon on 12/12/2017.
 */

public class OdooBaseDatosInvoices extends AsyncTask<String, Object, Object[]>{
    /*************LISTA VARIABLES******************/
    private XMLRPCClient client;
    private static final String MODELO_PARTNER = "res.partner";
    private static final String MODELO_INVOICES = "account.invoice";
    private static final String MODELO_PAGOS = "account.voucher";
    private String modelo_seleccionado;
    private String urlServidorOdoo, baseDatos, usuario, pass;
    private int idUsuarioOdoo;
    private ArrayList<String>[] filtros ;
    private HashMap<String,ArrayList<String>> mapeoCampos;
    private HashMap<String,String>[] mapeoResultado;
    private ClaseDialogos dialogos;
    private ListView listView;
    private Context ctx;
    private VistaListaPersonalizada listaFacturasOdoo;
    /*************LISTA VARIABLES******************/

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    /**
     * Metodo para seleccionar que modelo es el que se esta trabajando
     * en odoo
     * MODELO: 0 - MODELO PARTNER
     * MODELO: 1 - MODELO PAGOS
     * MODELO: 2 - MODELO FACTURAS
     * @param model
     */
    public void selectModel(int model){
        switch(model){
            case(0):
                modelo_seleccionado = MODELO_PARTNER;
                break;
            case(1):
                modelo_seleccionado = MODELO_PAGOS;
                break;
            case(2):
                modelo_seleccionado = MODELO_INVOICES;
                break;
        }
    }

    /**
     * Seteo del mapeo de los campos seleccionados
     * @param mapeoCampos
     */
    public void setMapeoCampos(HashMap<String, ArrayList<String>> mapeoCampos) {
        this.mapeoCampos = mapeoCampos;
    }

    public ArrayList<String>[] getFiltros() {
        return filtros;
    }

    /**
     * Seteo de filtros en forma de ArrayList[]
     * @param filtros
     */
    public void setFiltros(ArrayList<String>[] filtros) {
        this.filtros = filtros;
    }
    private static XMLRPCCallback listener;

    /**
     * Seteo de datos basicos para el ingreso a Odoo
     * @param urlServidorOdoo
     * @param baseDatos
     * @param usuario
     * @param pass
     * @param idUsuarioOdoo
     */
    public OdooBaseDatosInvoices(Context ctx, String urlServidorOdoo,
                                 String baseDatos, String usuario, String pass, int idUsuarioOdoo) {
        this.ctx = ctx;
        this.urlServidorOdoo = urlServidorOdoo;
        this.baseDatos = baseDatos;
        this.usuario = usuario;
        this.pass = pass;
        this.idUsuarioOdoo = idUsuarioOdoo;
        dialogos = new ClaseDialogos(ctx);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialogos.mostrarProgresoDialogo();
        dialogos.progresoDialogo("Cargando","Cargando facturas, aguarde");
    }

    @Override
    protected Object[] doInBackground(String... strings) {
        Object[] result=null;
        try{
            client = new XMLRPCClient(new URL(urlServidorOdoo), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_CERT);

            result = (Object[]) client.call("execute_kw",baseDatos,idUsuarioOdoo,pass,
                modelo_seleccionado,"search_read",
                Arrays.asList(Arrays.asList(filtros)),mapeoCampos);
            publishProgress(result);
        } catch (Exception e) {

        }
        return result;

    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        mapeoResultado = new HashMap[values.length];
        for(int i=0;i<values.length;i++){
            mapeoResultado[i] = new HashMap<>();
            mapeoResultado[i].putAll((HashMap<String,String>) values[i]);
            Log.e("ERSDXCXCSD",mapeoResultado[i].toString());
        }
    }

    @Override
    protected void onPostExecute(Object[] objects) {
        super.onPostExecute(objects);
        HashMapOdooInvoices.setResultadoInvoices(mapeoResultado);
        listaFacturasOdoo = new VistaListaPersonalizada(ctx,objects);
        listView.setAdapter(listaFacturasOdoo);
        dialogos.cerrarProgresoDialogo();
    }

}
