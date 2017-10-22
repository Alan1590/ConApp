package com.conapp.alangon.conapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.conapp.alangon.basedatos.TrabajoBaseDatos;

public class nuevo_usuarioActivity extends AppCompatActivity {
    private TrabajoBaseDatos baseDatos;
    TextView txt_nombre;
    TextView txt_email;
    TextView txt_telefono;
    TextView txt_direccion;
    TextView txt_usuario;
    TextView txt_password;
    TextView txt_checkpassword;
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
        baseDatos = new TrabajoBaseDatos();

        btnFinalizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crearUsuario(view);
            }
        });

    }

    private void crearUsuario(View v){
        Boolean isCreado = crearUsuario(
                txt_nombre.getText().toString(),
                txt_email.getText().toString(),
                txt_telefono.getText().toString(),
                txt_direccion.getText().toString(),
                txt_usuario.getText().toString(),
                txt_password.getText().toString(),
                txt_checkpassword.getText().toString()

        );
        if(isCreado){
            AlertDialog.Builder crearUsuario = new AlertDialog.Builder(nuevo_usuarioActivity.this);
            crearUsuario.setTitle("Info").setMessage("El usuario se creó con éxito");
            crearUsuario.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intMain = new Intent(nuevo_usuarioActivity.this,MainActivity.class);
                    startActivity(intMain);

                }
            }).create().show();
        }else{
            AlertDialog.Builder errorAlCrearUsuario = new AlertDialog.Builder(nuevo_usuarioActivity.this);
            errorAlCrearUsuario.setTitle("Info").setMessage("No se puede crear el usuario");
            errorAlCrearUsuario.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).create().show();
        }

    }

    /**
     * Funcion para agregar usuario en la base de datos postgresql
     *
     */
    private Boolean crearUsuario(String nombre, String email, String telefono, String direccion, String user, String pass, String checkPass){
        Boolean passAceptado = this.validacionPassword(pass, checkPass);
        Boolean validacionCampos = this.validacionCampos(nombre,telefono,user,pass,checkPass);
        if(passAceptado & validacionCampos){
            baseDatos.datosUsuario(
                    nombre,
                    email,
                    telefono,
                    direccion,
                    user,
                    pass
            );
            return true;
        }else{
            return false;
        }

    }

    private boolean validacionPassword(String pass1, String pass2){
        if(pass1.equals(pass2)){

            return true;
        }else{
            AlertDialog.Builder errorPass = new AlertDialog.Builder(this);
            errorPass.setTitle("Error").setMessage("No coinciden los Password").create().show();
            txt_password.setText("");
            txt_checkpassword.setText("");
            return false;
        }
    }

    private boolean validacionCampos(String campoNombre, String campoTelefono,
                                     String campoUsuario, String campoPass, String campoPassCheck){
        if(campoNombre.isEmpty() || campoTelefono.isEmpty() ||
                campoUsuario.isEmpty() || campoPass.isEmpty() || campoPassCheck.isEmpty()){
            AlertDialog.Builder errorValidarCampos = new AlertDialog.Builder(nuevo_usuarioActivity.this);
            errorValidarCampos.setTitle("Info").setMessage("Los campos marcados con celeste son obligatorios");
            errorValidarCampos.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    txt_nombre.setBackgroundColor(getResources().getColor(R.color.celeste_campos));
                    txt_telefono.setBackgroundColor(getResources().getColor(R.color.celeste_campos));
                    txt_usuario.setBackgroundColor(getResources().getColor(R.color.celeste_campos));
                    txt_password.setBackgroundColor(getResources().getColor(R.color.celeste_campos));
                    txt_checkpassword.setBackgroundColor(getResources().getColor(R.color.celeste_campos));
                }
            }).create().show();
            return false;
        }else{
            return true;
        }

    }



}
