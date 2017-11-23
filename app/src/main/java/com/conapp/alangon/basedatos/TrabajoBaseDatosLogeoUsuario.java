package com.conapp.alangon.basedatos;

import android.os.AsyncTask;

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
    private boolean hayFallas;
    private String errores;

    @Override
    protected Boolean doInBackground(String... strings) {
        return null;
    }
    
    /**
     * Funcion que se va a llamar siempre para la conexion a la base de datos
     */
    private void conectarALaBaseDatos() {
        try {
            Class.forName("org.postgresql.Driver");
            // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");

            conn = DriverManager.getConnection(
                    "jdbc:postgresql://mvbox.ddns.net/conapp", "conapp", "C0n@pp#2017AWG");
        } catch (SQLException | ClassNotFoundException e) {
            hayFallas = true;
            errores=e.getMessage();
        }
    }


}
