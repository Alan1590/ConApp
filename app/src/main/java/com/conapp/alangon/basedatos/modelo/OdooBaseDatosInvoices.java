package com.conapp.alangon.basedatos.modelo;

/**
 * Created by Alan Gon on 12/12/2017.
 */

public class OdooBaseDatosInvoices {

    String itemNombre;
    String itemFecha;
    int itemMonto;
    boolean itemPagado;

    public String getItemNombre() {
        return itemNombre;
    }

    public void setItemNombre(String itemNombre) {
        this.itemNombre = itemNombre;
    }

    public String getItemFecha() {
        return itemFecha;
    }

    public void setItemFecha(String itemFecha) {
        this.itemFecha = itemFecha;
    }

    public int getItemMonto() {
        return itemMonto;
    }

    public void setItemMonto(int itemMonto) {
        this.itemMonto = itemMonto;
    }

    public boolean isItemPagado() {
        return itemPagado;
    }

    public void setItemPagado(boolean itemPagado) {
        this.itemPagado = itemPagado;
    }
}
