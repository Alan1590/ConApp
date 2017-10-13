package com.conapp.alangon.basedatos;

/**
 * Created by Alan Gon on 05/10/2017.
 */
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrabajoBaseDatos {

    public void TrabajoBaseDatos(){
        sqlConect.start();



        }

    Thread sqlConect = new Thread() {
        public void run() {
            try {
                Class.forName("org.postgresql.Driver");
                // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
                // Si estás utilizando el emulador de android y tenes el PostgreSQL en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://192.168.0.244:5432/conapp", "odoo", "odoo1590");
                //En el stsql se puede agregar cualquier consulta SQL deseada.
                String stsql = "Select version()";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(stsql);
                rs.next();
                Log.d("CONESIONXCASDASD",rs.getString(1));
                conn.close();

            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };
}
