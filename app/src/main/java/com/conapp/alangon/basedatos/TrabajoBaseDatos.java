package com.conapp.alangon.basedatos;

/**
 * Created by Alan Gon on 05/10/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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

import com.conapp.alangon.conapp.Nuevo_usuarioActivity;
import com.conapp.alangon.personalizaciones.ClaseErrores;
import com.conapp.alangon.validacion.ValidacionesNuevoUsuario;

public class
TrabajoBaseDatos extends AsyncTask<String, String, Boolean> {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet result;
    private PreparedStatement pstm;
    private boolean hayFallas;
    private String errores, deviceId;
    private int accion;
    private ClaseErrores mjsError;
    private Nuevo_usuarioActivity nuevo_usuarioActivity;
    private ValidacionesNuevoUsuario validacionesNuevoUsuario;

    /**
     * Seteo del tipo de accion a realizar
     * 0 - Para la creacion de usuarios
     * 1 - Para chequear que el imei exista
     *
     * @param accion
     */
    public void setAccion(int accion) {
        this.accion = accion;
    }

    /**
     * Seteo de la actividad nuevo usuario para poder pasar los parametros para saber si existe o no el IMEI
     *
     * @param nuevo_usuarioActivity
     */
    public void setNuevo_usuarioActivity(Nuevo_usuarioActivity nuevo_usuarioActivity) {
        this.nuevo_usuarioActivity = nuevo_usuarioActivity;
    }

    public void setValidacionesNuevoUsuario(ValidacionesNuevoUsuario validacionesNuevoUsuario) {
        this.validacionesNuevoUsuario = validacionesNuevoUsuario;
    }

    /**
     * Constructor donde se pasa el contexto de la app
     *
     * @param ctx
     */
    public TrabajoBaseDatos(Context ctx) {
        mjsError = new ClaseErrores(ctx);
    }

    /**
     * Funcion que se ejecuta en segundo plano en el que dependiendo de la accion va a ejecutar una tarea especifica
     *
     * @param strings
     * @return
     */
    @Override
    protected Boolean doInBackground(String... strings) {
        conectarALaBaseDatos();
        switch (accion) {
            case (0):
                crearUsuario(strings);
                break;
            case (1):
                getDeviceId(strings[0]);
                break;
        }
        return hayFallas;
    }

    /**
     * Funcion que se va a ejecutar una vez finalizado el Thread, en este caso si el imei existe en la base de datos
     * finaliza. En caso contrario crea el nuevo usuario
     *
     * @param result
     */
    @Override
    protected void onPostExecute(Boolean result) {
        if (validacionesNuevoUsuario.isValidacionNuevoUsuario() && accion == 1) {
            nuevo_usuarioActivity.crearUsuario(deviceId);
        }else if(!validacionesNuevoUsuario.isValidacionNuevoUsuario()  && accion == 1) {
            mjsError.mensajeError("Error","El usuario ya se encuentra registrado");
        }
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
        } catch (SQLException se) {
            hayFallas = true;
        } catch (ClassNotFoundException e) {
            hayFallas = true;
        }
    }

    /**
     * Funcion que se va a llamar cuando se cree un nuevo usuario, este mismo recibira parametros tales como Nombre, email, etc.
     *
     * @param strings
     */
    private void crearUsuario(String... strings) {

        try {

            st = conn.prepareStatement("Insert into public.users (user_name,user_email,user_address,user_user," +
                    "user_password,user_idodoo,user_ctasoftguard,user_telephone,user_imei,user_habilitado) values(?,?,?,?,?,?,?,?,?,?)");
            st.setString(1, strings[0]); //- Nombre
            st.setString(2, strings[1]); //- Email
            st.setString(3, strings[2]); //- Direccion
            st.setString(4, strings[3]); //- Usuario
            st.setString(5, strings[4]); //- Password
            st.setInt(6, 0); //- Id usuario en odoo
            st.setInt(7, 0); //- Id usuario en softguard
            st.setString(8, strings[5]); //- Telefono
            st.setString(9, strings[6]); //- Imei
            st.setBoolean(10, false);
            st.execute();
            validacionesNuevoUsuario.setValidacionNuevoUsuario(false);
        } catch (SQLException sqlE) {
            hayFallas = true;
        }
    }

    /**
     * Busca el imei en la base de datos
     *
     * @param device
     */
    private void getDeviceId(String device) {
        byte numeroResultados = 0;
        deviceId = device;
        try {
            pstm = conn.prepareStatement("Select * from public.users where user_imei = ?");
            pstm.setString(1, device);
            result = pstm.executeQuery();
            while (result.next()) {
                numeroResultados++;
                break;
            }
            if (numeroResultados == 0) {
                validacionesNuevoUsuario.setValidacionNuevoUsuario(true);
            } else {
                validacionesNuevoUsuario.setValidacionNuevoUsuario(false);
            }
        } catch (SQLException eq) {
            hayFallas = true;
        }
    }
}
