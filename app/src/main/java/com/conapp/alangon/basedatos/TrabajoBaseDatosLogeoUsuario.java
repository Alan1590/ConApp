package com.conapp.alangon.basedatos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.conapp.alangon.personalizaciones.ClaseDialogos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alan Gon on 23/11/2017.
 */

public class TrabajoBaseDatosLogeoUsuario extends AsyncTask<String, String, Boolean> {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet result;
    private PreparedStatement pstm;
    private boolean hayFallas, logeoExitoso;
    private String errores;
    private ClaseDialogos dialogos;
    private static int idUsuario, idClientOdoo;

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        TrabajoBaseDatosLogeoUsuario.idUsuario = idUsuario;
    }

    public TrabajoBaseDatosLogeoUsuario(Context ctx) {
        dialogos = new ClaseDialogos(ctx);
    }


    @Override
    protected Boolean doInBackground(String... strings) {
        publishProgress(strings);
        if(conectarALaBaseDatos()){
            logeoUsuario(strings);
        }
        return logeoExitoso;
    }

    @Override
    protected void onPostExecute(Boolean result){
        if(!logeoExitoso && !hayFallas){
            dialogos.cerrarProgresoDialogo();
            dialogos.mensajeError("Error","Usuario o contraseña incorrecto");
        }else if (!logeoExitoso && hayFallas){
            dialogos.cerrarProgresoDialogo();
            dialogos.mensajeError("Error", errores);
        }
        dialogos.cerrarProgresoDialogo();

    }


    @Override
    protected void onProgressUpdate(String... strings){
        dialogos.progresoDialogo("Ingresando","Aguarde");
    }


    /**
     * Funcion que se va a llamar siempre para la conexion a la base de datos
     */
    private boolean conectarALaBaseDatos() {
        boolean conectado;
        try {
            Class.forName("org.postgresql.Driver");
            // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");

            conn = DriverManager.getConnection(
                    "jdbc:postgresql://mvbox.ddns.net/conapp", "conapp", "C0n@pp#2017AWG");
            conectado=true;
        } catch (SQLException | ClassNotFoundException e) {
            hayFallas = true;
            conectado=false;
            errores=e.getMessage();
        }
        return conectado;
    }

    private void logeoUsuario(String... datosLogeo) {
        try {
            pstm = conn.prepareStatement("Select * from public.users where user_user = ? and user_password = ? and user_imei=?");
            pstm.setString(1, datosLogeo[0]);
            pstm.setString(2, datosLogeo[1]);
            pstm.setString(3, datosLogeo[2]);
            result = pstm.executeQuery();
            while (result.next()) {
                logeoExitoso = true;
                idUsuario = result.getInt("id");
                idClientOdoo = result.getInt("idclient_odoo");
                break;
            }
        } catch (SQLException eq) {
            hayFallas = true;
            errores=eq.getMessage();
        }
    }


}
