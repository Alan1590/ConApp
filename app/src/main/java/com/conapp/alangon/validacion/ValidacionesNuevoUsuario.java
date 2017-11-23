package com.conapp.alangon.validacion;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.conapp.alangon.personalizaciones.ClaseDialogos;

/**
 * Created by Alan Gon on 22/11/2017.
 */

public class ValidacionesNuevoUsuario {
    private boolean validacionPassword, validacionCampos, validacionNuevoUsuario, validacionLongitudPass;
    private String largoPassword;
    private Context ctx;
    private ClaseDialogos msjDialogo;
    public ValidacionesNuevoUsuario(Context ctx) {
        this.ctx = ctx;
        msjDialogo = new ClaseDialogos(ctx);
    }

    public boolean isValidacionLongitudPass() {
        return validacionLongitudPass;
    }

    public void setValidacionLongitudPass(boolean validacionLongitudPass) {
        this.validacionLongitudPass = validacionLongitudPass;
    }

    public boolean isValidacionPassword() {
        return validacionPassword;
    }

    public void setValidacionPassword(boolean validacionPassword) {
        this.validacionPassword = validacionPassword;
    }

    public boolean isValidacionCampos() {
        return validacionCampos;
    }

    public void setValidacionCampos(boolean validacionCampos) {
        this.validacionCampos = validacionCampos;
    }

    public boolean isValidacionNuevoUsuario() {
        return validacionNuevoUsuario;
    }

    public void setValidacionNuevoUsuario(boolean validacionNuevoUsuario) {
        this.validacionNuevoUsuario = validacionNuevoUsuario;
    }

    private void existeUsuario(){
        if(!validacionNuevoUsuario){
            msjDialogo.mensajeError("Error","Este usuario ya esta registrado en el sistema");
        }
    }

    public boolean validacionLongitudPassword(Context ctx, String password){
        boolean validacionLongitudPass;
        if(password.toCharArray().length > 5){
            validacionLongitudPass=true;
        }else{
            validacionLongitudPass=false;
        }
        return validacionLongitudPass;
    }

    public boolean permitirCrearUsuario(){
        boolean permitirCrear;
        if(validacionCampos && validacionPassword && validacionNuevoUsuario && validacionLongitudPass){
            permitirCrear = true;
        }else{
            permitirCrear=false;
        }
        return permitirCrear;
    }
}
