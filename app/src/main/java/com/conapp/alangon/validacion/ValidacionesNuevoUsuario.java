package com.conapp.alangon.validacion;

import android.content.Context;

/**
 * Created by Alan Gon on 22/11/2017.
 */

public class ValidacionesNuevoUsuario {
    private boolean validacionPassword, validacionCampos, validacionNuevoUsuario;
    private String largoPassword;

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

    public boolean validacionLongitudPassword(Context ctx, String password){
        boolean validacionLongitudPass;
        if(password.toCharArray().length < 10){
            validacionLongitudPass=true;
        }else{
            validacionLongitudPass=false;
        }
        return validacionLongitudPass;
    }

    public boolean permitirCrearUsuario(){
        boolean permitirCrear;
        if(validacionCampos && validacionPassword && validacionNuevoUsuario){
            permitirCrear = true;
        }else{
            permitirCrear=false;
        }
        return permitirCrear;
    }
}
