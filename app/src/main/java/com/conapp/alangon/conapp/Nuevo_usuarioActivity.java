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

import com.conapp.alangon.basedatos.TrabajoBaseDatosRegistroUsuario;
import com.conapp.alangon.personalizaciones.ClaseDialogos;
import com.conapp.alangon.validacion.ValidacionesNuevoUsuario;

//CLASE PARA LA CREACION DE UN NUEVO USUARIO
public class Nuevo_usuarioActivity extends AppCompatActivity {
    private TextView txt_nombre,txt_email,txt_telefono,txt_direccion,txt_usuario,txt_password,txt_checkpassword;
    private ValidacionesNuevoUsuario validacionesNuevoUsuario;

    private ClaseDialogos msjErrores;
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
        msjErrores = new ClaseDialogos(this);
        validacionesNuevoUsuario = new ValidacionesNuevoUsuario(this);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validacionCampos();
            }
        });

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
            validacionesNuevoUsuario.setValidacionCampos(false);
            return false;
        }else{
            validacionesNuevoUsuario.setValidacionCampos(true);
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
        if (pass1.equals(pass2)) {
            validacionesNuevoUsuario.setValidacionPassword(true);
            validacionTamañoPass();
        }else{
            msjErrores.mensajeError("Error", "No coinciden los Password");
            txt_password.setText("");
            txt_checkpassword.setText("");
            validacionesNuevoUsuario.setValidacionPassword(false);
        }
        return coincidePass;
    }

    //PASO VALIDACION 3
    /**
     * Funcion para chequear tamaño de password que sea mayor a 10
     */
    private void validacionTamañoPass(){
        boolean validacionLongitudPass = validacionesNuevoUsuario.validacionLongitudPassword(this,txt_password.getText().toString());

        if(validacionLongitudPass){
            validacionesNuevoUsuario.setValidacionLongitudPass(true);
            getDeviceId();
        }else{
            validacionesNuevoUsuario.setValidacionLongitudPass(false);
            msjErrores.mensajeError("Error", "La contraseña debe superar los 10 caracteres");
        }

    }

    //PASO 4 CHEQUEAR SI EL IME EXISTE
    /**
     * Funcion para obtener el imei del dispositivo
     * @return
     */
    private String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = "";
        TrabajoBaseDatosRegistroUsuario baseDatos = new TrabajoBaseDatosRegistroUsuario(this);
        try{
            deviceId = tm.getDeviceId();
            baseDatos.setNuevo_usuarioActivity(this);
            baseDatos.setValidacionesNuevoUsuario(validacionesNuevoUsuario);
            baseDatos.setAccion(1);
            baseDatos.execute(tm.getDeviceId());

        }catch (SecurityException e){
            msjErrores.mensajeError("Error", e.getMessage());
        }
        return deviceId;
    }

    /**
     * Funcion para agregar usuario en la base de datos postgresql
     * Chequeo de 4 validaciones para poder crearse el usuario:
     * - Todos los campos requeridos completos
     * - Los password 1 y 2 coinciden
     * - El tamaño de password sea mayor de 10
     * - El imei no existe en la base de datos
     */
    public void crearUsuario(String deviceId){
        TrabajoBaseDatosRegistroUsuario baseDatos = new TrabajoBaseDatosRegistroUsuario(this);
        if (validacionesNuevoUsuario.permitirCrearUsuario()) {

            String[] datosUsuario = new String[7];
            datosUsuario[0] = txt_nombre.getText().toString();
            datosUsuario[1] = txt_email.getText().toString();
            datosUsuario[2] = txt_direccion.getText().toString();
            datosUsuario[3] = txt_usuario.getText().toString();
            datosUsuario[4] = txt_password.getText().toString();
            datosUsuario[5] = txt_telefono.getText().toString();
            datosUsuario[6] = deviceId;
            baseDatos.setValidacionesNuevoUsuario(validacionesNuevoUsuario);
            baseDatos.setNuevo_usuarioActivity(this);
            baseDatos.setAccion(0);
            baseDatos.execute(datosUsuario);
            txt_nombre.setText("");
            txt_email.setText("");
            txt_telefono.setText("");
            txt_direccion.setText("");
            txt_usuario.setText("");
            txt_password.setText("");
            txt_checkpassword.setText("");
            AlertDialog.Builder crearUsuario = new AlertDialog.Builder(this);
            crearUsuario.setTitle("Info").setMessage("El usuario se creó con éxito");
            crearUsuario.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intMain = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intMain);
                }
            }).create().show();

        }

    }

}
