package com.conapp.alangon.personalizaciones;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alan Gon on 15/12/2017.
 */

public class FiltrosParaOdoo {
    /*************LISTA VARIABLES******************/

    /******LISTADO CAMPOS INVOICE******/
    private final String CAMPO_INVOICE_AMOUNT = "amount_total";
    private final String CAMPO_INVOICE_NUMBER = "number";
    private final String CAMPO_INVOICE_DATE = "date_invoice";
    private final String CAMPO_INVOICE_COMMENT = "comment";

    /******LISTADO CAMPOS PARTNER******/
    private final String CAMPO_PARTNER_NAME = "name";
    private final String CAMPO_PARTNER_PHONE = "phone";
    private final String CAMPO_PARTNER_MOBILE = "mobile";
    private final String CAMPO_PARTNER_EMAIL = "email";
    private final String CAMPO_PARTNER_STREET = "street";
    private final String CAMPO_INVOICE_CITY = "city";
    private final String CAMPO_INVOICE_STATE = "state_id";

    /******LISTADO CAMPOS PAGOS******/
    private final String CAMPO_PAGOS_NUMBER = "number";
    private final String CAMPO_PAGOS_TOTAL = "net_amount";
    private final String CAMPO_PAGOS_DATE = "date";
    private final String CAMPO_PAGOS_REFERENCIA = "reference";
    private final String CAMPO_PAGOS_LINEAS_PAGOS = "line_cr_ids";

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

    public void addCampos(int seleccion_modelo){
        switch(seleccion_modelo){
            case(0):
                listadoCampos.add(CAMPO_PARTNER_NAME);
                listadoCampos.add(CAMPO_PARTNER_PHONE);
                listadoCampos.add(CAMPO_PARTNER_MOBILE);
                listadoCampos.add(CAMPO_PARTNER_EMAIL);
                listadoCampos.add(CAMPO_PARTNER_STREET);
                listadoCampos.add(CAMPO_INVOICE_CITY);
                listadoCampos.add(CAMPO_INVOICE_STATE);
            case(1):
                listadoCampos.add(CAMPO_PAGOS_NUMBER);
                listadoCampos.add(CAMPO_PAGOS_TOTAL);
                listadoCampos.add(CAMPO_PAGOS_DATE);
                listadoCampos.add(CAMPO_PAGOS_REFERENCIA);
                listadoCampos.add(CAMPO_PAGOS_LINEAS_PAGOS);
            case(2):
                listadoCampos.add(CAMPO_INVOICE_AMOUNT);
                listadoCampos.add(CAMPO_INVOICE_NUMBER);
                listadoCampos.add(CAMPO_INVOICE_DATE);
                listadoCampos.add(CAMPO_INVOICE_COMMENT);

        }

    }

    public ArrayList<String>[] getFiltro(){
        return listadoFiltro;
    }

    public HashMap<String, ArrayList<String>> getMapeoCampos() {
        mapeoCampos.put("fields",listadoCampos);
        return mapeoCampos;
    }


}
