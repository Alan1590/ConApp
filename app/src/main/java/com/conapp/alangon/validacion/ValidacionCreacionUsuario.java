package com.conapp.alangon.validacion;

/**
 * Created by Alan Gon on 21/11/2017.
 */

public class ValidacionCreacionUsuario {
    private static boolean validacionCampos, validacionPassword, validacionImei;

    public static void setValidacionCampos(boolean validacionCampos) {
        ValidacionCreacionUsuario.validacionCampos = validacionCampos;
    }

    public static void setValidacionPassword(boolean validacionPass) {
        ValidacionCreacionUsuario.validacionPassword = validacionPass;
    }

    public static void setValidacionImei(boolean validacionImei) {
        ValidacionCreacionUsuario.validacionImei = validacionImei;
    }
}
