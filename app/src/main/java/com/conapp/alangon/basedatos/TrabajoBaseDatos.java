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
    private String deviceId;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet result;
    private boolean usuarioCreado;
    private boolean conexionExitosa;

    public boolean isUsuarioCreado() {
        return usuarioCreado;
    }

    public boolean isConexionExitosa() {
        return conexionExitosa;
    }

    public void datosUsuario(String nombre, String email, String telefono, String direccion, String usuario, String pass, String deviceId){
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.pass = pass;
        this.deviceId = deviceId;
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
                        "jdbc:postgresql://192.168.0.20/conapp", "talento", "talento3546");
                interrupt();
                conexionExitosa = true;
            } catch (SQLException se) {
                Log.e("ERRRRRR", se.getMessage());
                conexionExitosa = false;
                interrupt();
            } catch (ClassNotFoundException e) {
                conexionExitosa = false;
                interrupt();
            }
        }
    };

    public boolean dispositivoExistente(){
        return false;
    }

    Thread sqlCrearUsuario = new Thread(){
        public void run(){
            try{

                st = conn.prepareStatement("Insert into public.users (user_name,user_email,user_address,user_user," +
                    "user_password,user_idodoo,user_ctasoftguard,user_telephone,user_imei,user_habilitado) values(?,?,?,?,?,?,?,?,?,?)");
                st.setString(1,nombre);
                st.setString(2,email);
                st.setString(3,direccion);
                st.setString(4,usuario);
                st.setString(5,pass);
                st.setInt(6,0);
                st.setInt(7,0);
                st.setString(8,telefono);
                st.setString(9,deviceId);
                st.setBoolean(10,false);
                st.execute();
                usuarioCreado = true;
                interrupt();
            }catch (SQLException sqlE){
                interrupt();
                Log.e("ERRRRRRRR",sqlE.getMessage());
                usuarioCreado=false;
            }
        }
    };

    Thread sqlCorroborarUsuario = new Thread(){
        public  void run(){
            try{
                st = conn.prepareStatement("Select * from public.users where user_imei = ?");
                st.setString(1,"359931070334522");
                result = st.executeQuery();
                while(result.next()){
                    Log.e("IMEI", result.getString(2));
                }

            }catch(SQLException eq){

            }
        }

    };

    private int idUsaurio(){
        return 0;
    }
}
