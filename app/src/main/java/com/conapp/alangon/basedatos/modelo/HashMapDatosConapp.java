package com.conapp.alangon.basedatos.modelo;

import java.util.HashMap;

/**
 * Created by Alan Gon on 26/12/2017.
 */

public class HashMapDatosConapp {
    public  static HashMap<String,String> resultados;

    public static HashMap<String, String> getResultados() {
        return resultados;
    }

    public static void setResultados(HashMap<String, String> getResultados) {
        HashMapDatosConapp.resultados = getResultados;
    }
}
