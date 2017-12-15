package com.conapp.alangon.Odoo;

import android.util.Log;
import com.conapp.alangon.Odoo.axmlrpc.*;
import com.conapp.alangon.Odoo.axmlrpc.serializer.StringSerializer;
import com.conapp.alangon.basedatos.modelo.OdooBaseDatosInvoices;
import com.conapp.alangon.conapp.MainActivity;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alan Gon on 13/10/2017.
 */

public class XmlRpcOdoo {
    private static String urlServidorOdoo;
    private static int idUsuarioOdoo;
    private static String baseDatos;
    private static String usuario;
    private static String pass;
    private XMLRPCClient client;
    private static XMLRPCCallback listener;
    private static ArrayList<Object> listRecords;

    public static ArrayList<Object> getListRecords() {
        return listRecords;
    }

    public ArrayList<Object> getlistRecords() {
        return listRecords;
    }

    public static String getBaseDatos() {
        return baseDatos;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getPass() {
        return pass;
    }

    public static int getIdUsuarioOdoo() {
        return idUsuarioOdoo;
    }

    public static String getUrlServidorOdoo() {
        return urlServidorOdoo;
    }

    public XmlRpcOdoo(String urlServidorOdoo, String usuario, String pass, String baseDatos, int idUsuarioOdoo) {
        this.usuario = usuario;
        this.pass = pass;
        this.baseDatos = baseDatos;
        this.urlServidorOdoo = urlServidorOdoo;
        this.idUsuarioOdoo = idUsuarioOdoo;
        try{
            client = new XMLRPCClient(new URL(urlServidorOdoo), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_CERT);

        }catch (MalformedURLException urlEx){

        }
    }

    /**
     * Logeo de usuario que esta dado por la base de datos principal
     * @param id
     */
    private boolean login(int id){
        return false;
    }

    /**
     * Devuelve un array de ids dependiendo el modelo
     * @param filtro
     * @param modelo
     * @return
     */
    public ArrayList<Object> listRecords(String[] filtro, String modelo){
        return null;
    }


   /**
     * Funcion que devuelve un array de Objectos segun la consulta que se haga en odoo
     * @param model
     * @param filtro
     * @return
     */
/*    private Object[] filtro(String model, Object[] filtro){
        try{
            XMLRPCClient client = new XMLRPCClient(new URL("url"), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_CERT);
        return null;
    }
*/



}
