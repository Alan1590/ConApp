package com.conapp.alangon.basedatos.modelo;

import java.util.HashMap;

/**
 * Created by AlanMSI on 9/11/2017.
 */

public class OdooBaseDatosConapp {
    private static HashMap<String, Object> mapeoResultado;

    public static HashMap<String, Object> getMapeoResultado() {
        return mapeoResultado;
    }

    public static void setMapeoResultado(HashMap<String, Object> mapeoResultado) {
        OdooBaseDatosConapp.mapeoResultado = mapeoResultado;
    }
}
