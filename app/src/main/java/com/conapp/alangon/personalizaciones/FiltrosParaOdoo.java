package com.conapp.alangon.personalizaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alan Gon on 15/12/2017.
 */

public class FiltrosParaOdoo {
    /*************LISTA VARIABLES******************/
    private ArrayList<String> filtro = new ArrayList<>();
    private String[] busqueda = new String[3];
    private int maximoFiltros;
    private ArrayList<String>[] listadoFiltro;
    private ArrayList<String> listadoCampos;
    private HashMap<String,ArrayList<String>> mapeoCampos;
    /*************LISTA VARIABLES******************/

    public FiltrosParaOdoo(int maximoFiltros) {
        this.maximoFiltros = maximoFiltros;
        listadoFiltro = new ArrayList[maximoFiltros];
        listadoCampos = new ArrayList<>();
        mapeoCampos = new HashMap<>();

    }

    public void addFiltro(int numeroFiltro, String campo, String operador, String condicion){
        listadoFiltro[numeroFiltro] = new ArrayList<>();
        listadoFiltro[numeroFiltro].add(campo);
        listadoFiltro[numeroFiltro].add(operador);
        listadoFiltro[numeroFiltro].add(condicion);
    }

    public void addCampos(String campo){
        listadoCampos.add(campo);
    }

    public ArrayList<String>[] getFiltro(){
        return listadoFiltro;
    }

    public HashMap<String, ArrayList<String>> getMapeoCampos() {
        mapeoCampos.put("fields",listadoCampos);
        return mapeoCampos;
    }


}
