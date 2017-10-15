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

public class
TrabajoBaseDatos {

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
                String stsql = "INSERT INTO public.user (user_name,user_email,user_telephone,user_address,user_user,user_password) " +
                        "VALUES ('alan','alan',15424,'adsasdas','adsad','adasd12')";
                Statement st = conn.createStatement();
                st.executeUpdate(stsql);
                conn.close();

            } catch (SQLException se) {
                Log.e("ERRR",se.getMessage());
            } catch (ClassNotFoundException e) {
                Log.e("ERRR",e.getMessage());
            }
        }
    };

    private int idUsaurio(){
        return 0;
    }
}
