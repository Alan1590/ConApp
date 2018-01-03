package com.conapp.alangon.basedatos.modelo;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alan Gon on 31/12/2017.
 */

public class HashMapOdooInvoices {
    private static HashMap<String,String>[] resultadoInvoices;
    public static HashMap<String, String>[] getResultadoInvoices() {
        return resultadoInvoices;
    }

    public static void setResultadoInvoices(HashMap<String, String>[] resultadoInvoices) {
        HashMapOdooInvoices.resultadoInvoices = resultadoInvoices;
    }
}
