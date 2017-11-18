package com.conapp.alangon.conapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.conapp.alangon.basedatos.TrabajoBaseDatos;
import com.conapp.alangon.personalizaciones.ClaseErrores;
//CLASE PARA LA CREACION DE UN NUEVO USUARIO
public class nuevo_usuarioActivity extends AppCompatActivity {
    private TrabajoBaseDatos baseDatos;
    private TextView txt_nombre,txt_email,txt_telefono,txt_direccion,txt_usuario,txt_password,txt_checkpassword;
    private boolean validacionCampos, validacionPassword, validacionImei;

    private ClaseErrores msjErrores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        txt_nombre = (TextView) findViewById(R.id.editTextAgregarUsuario_Nombre);
        txt_email = (TextView) findViewById(R.id.editTextAgregarUsuario_Email);
        txt_telefono = (TextView) findViewById(R.id.editTextAgregarUsuario_Telefono);
        txt_direccion = (TextView) findViewById(R.id.editTextAgregarUsuario_Direccion);
        txt_usuario = (TextView) findViewById(R.id.editTextAgregarUsuario_User);
        txt_password = (TextView) findViewById(R.id.editTextAgregarUsuario_Password);
        txt_checkpassword = (TextView) findViewById(R.id.editTextAgregarUsuario_RepetirPassword);
        Button btnFinalizar =(Button) findViewById(R.id.buttonAgregarUsuario_Finalizar);
        baseDatos = new TrabajoBaseDatos(this);
        msjErrores = new ClaseErrores(this);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario();
            }
        });

    }

    /**
     * Funcion para agregar usuario en la base de datos postgresql
     * Chequeo de 3 validaciones para poder crearse el usuario:
     * - Todos los campos requeridos completos
     * - Los password 1 y 2 coinciden
     * - El imei no existe en la base de datos
     */
    private void crearUsuario(){
        Boolean camposCompletos = validacionCampos();

        if(validacionCreacion()) {
                baseDatos.datosUsuario(
                        txt_nombre.getText().toString(),
                        txt_email.getText().toString(),
                        txt_telefono.getText().toString(),
                        txt_direccion.getText().toString(),
                        txt_usuario.getText().toString(),
                        txt_password.getText().toString(),
                        getDeviceId()

                );

                txt_nombre.setText("");
                txt_email.setText("");
                txt_telefono.setText("");
                txt_direccion.setText("");
                txt_usuario.setText("");
                txt_password.setText("");
                AlertDialog.Builder crearUsuario = new AlertDialog.Builder(this);
                crearUsuario.setTitle("Info").setMessage("El usuario se creó con éxito");
                crearUsuario.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();

            }

    }

    //PASO VALIDACION 1
    /**
     * Funcion que chequea que los campos necesarios esten completos sino lo estan devuelve un mensaje de advertencia y se marcan los campos obligatorios
     * @return
     */
    private boolean validacionCampos(){
        boolean camposVacios = txt_nombre.getText().toString().isEmpty() || txt_telefono.getText().toString().isEmpty()
                || txt_password.getText().toString().isEmpty() || txt_checkpassword.getText().toString().isEmpty() || txt_usuario.getText().toString().isEmpty();
        if(camposVacios) {
            AlertDialog.Builder errorPass = new AlertDialog.Builder(this);
            errorPass.setTitle("Error").setMessage("Los campos marcados son obligatorios").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    txt_nombre.setBackgroundColor(Color.CYAN);
                    txt_telefono.setBackgroundColor(Color.CYAN);
                    txt_password.setBackgroundColor(Color.CYAN);
                    txt_checkpassword.setBackgroundColor(Color.CYAN);
                    txt_usuario.setBackgroundColor(Color.CYAN);
                }
            }).create().show();
            return false;
        }else{
            validacionCampos = true;
            validacionPassword(txt_password.getText().toString(),txt_checkpassword.getText().toString());
            return true;
        }
    }


    //PASO 2 VALIDACION
    /**
     * Funcion que corrobora si el pass1 y 2 son iguales si los son devuelve TRUE
     * @param pass1
     * @param pass2
     * @return
     */
    private boolean validacionPassword(String pass1, String pass2){
        boolean coincidePass=false;
        if(pass1.equals(pass2) ){
            coincidePass = true;
            validacionPassword = true;
            getDeviceId();
        }else{
            msjErrores.mensajeError("Error", "No coinciden los Password");
            txt_password.setText("");
            txt_checkpassword.setText("");
            coincidePass = false;
        }
        return coincidePass;
    }


    /**
     * Funcion para obtener el imei del dispositivo
     * @return
     */

    private String getDeviceId(){
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId="";
        try{
            deviceId = tm.getDeviceId();
            validacionIsNuevoUsuarioImei();
        }catch (SecurityException e){
            msjErrores.mensajeError("Error", e.getMessage());
        }
        return deviceId;
    }

    /**
     * Funcion para verificar si el usuario que se quiere registrar ya existe en la base de datos
     * @return
     */
    private boolean validacionIsNuevoUsuarioImei(){
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        boolean isNuevoUsuario=true;
        try{
            baseDatos.isNuevoUsuario(tm.getDeviceId());
            validacionImei = isNuevoUsuario;
        }catch (SecurityException e){
            msjErrores.mensajeError("Error", e.getMessage());
        }
        return  isNuevoUsuario;
    }

    private boolean validacionCreacion(){
        boolean crearUsuario = false;
        if(validacionImei && validacionPassword && validacionCampos){
            crearUsuario = true;
        }else{
            crearUsuario =false;
        }
        return crearUsuario;
    }

}
