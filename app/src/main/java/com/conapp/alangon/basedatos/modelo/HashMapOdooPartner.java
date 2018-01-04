package com.conapp.alangon.basedatos.modelo;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by Alan Gon on 04/01/2018.
 */

public class HashMapOdooPartner {
    private static HashMap<String,String>[] resultadoPartner;
    private static HashMap<String,Object> nombrePartner;

    public static String getNombre() {
        return nombrePartner.get("name").toString();
    }

    public static void setNombre(Object nombre) {
        nombrePartner = new HashMap<>();
        nombrePartner.putAll((HashMap<String,Object>)nombre);
    }

    public static HashMap<String, String>[] getResultadoPartner() {
        return resultadoPartner;
    }

    public static void setResultadoPartner(HashMap<String, String>[] resultadoPartner) {
        HashMapOdooPartner.resultadoPartner = resultadoPartner;
    }
}
