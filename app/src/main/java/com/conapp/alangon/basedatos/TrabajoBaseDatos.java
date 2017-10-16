package com.conapp.alangon.basedatos;

/**
 * Created by Alan Gon on 05/10/2017.
 */
import android.content.Context;
import android.provider.Telephony;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.telephony.TelephonyManager;

public class
TrabajoBaseDatos {
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String usuario;
    private String pass;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet result;

    public void datosUsuario(String nombre,String email,String telefono,String direccion,String usuario,String pass){
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.pass = pass;
        sqlCrearUsuario.start();

    }

    public TrabajoBaseDatos() {
        sqlConect.start();
    }

    Thread sqlConect = new Thread() {
        public void run() {
            try {
                Class.forName("org.postgresql.Driver");
                // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");

                conn = DriverManager.getConnection(
                        "jdbc:postgresql://192.168.0.244:5432/conapp", "odoo", "odoo1590");
                Log.e("Conexion",conn.getClientInfo().toString());
                interrupt();
            } catch (SQLException se) {
                Log.e("ERRR",se.getMessage());
                interrupt();
            } catch (ClassNotFoundException e) {
                Log.e("ERRR",e.getMessage());
                interrupt();
            }
        }
    };

    Thread sqlCrearUsuario = new Thread(){
        public void run(){
            try{

                st = conn.prepareStatement("Insert into public.user (user_name,user_email,user_address,user_user," +
                    "user_password,user_idodoo,user_ctasoftguard,user_telephone,user_imei,user_habilitado) values(?,?,?,?,?,?,?,?,?,?)");
                st.setString(1,nombre);
                st.setString(2,email);
                st.setString(3,direccion);
                st.setString(4,usuario);
                st.setString(5,pass);
                st.setInt(6,0);
                st.setInt(7,0);
                st.setString(8,telefono);
                st.setString(9,"");
                st.setBoolean(10,false);
                st.execute();
            }catch (SQLException sqlE){
                Log.e("ERRRRRRRR",sqlE.getMessage());
            }
        }
    };

    private int idUsaurio(){
        return 0;
    }
}
