package com.conapp.alangon.conapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.conapp.alangon.basedatos.TrabajoBaseDatos;

public class nuevo_usuarioActivity extends AppCompatActivity {
    private TrabajoBaseDatos baseDatos;
    private TextView txt_nombre;
    private TextView txt_email;
    private TextView txt_telefono;
    private TextView txt_direccion;
    private TextView txt_usuario;
    private TextView txt_password;
    private TextView txt_checkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        TextView txt_nombre = (TextView) findViewById(R.id.editTextAgregarUsuario_Nombre);
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
                crearUsuario();
            }
        });

    }

    /**
     * Funcion para agregar usuario en la base de datos postgresql
     *
     */
    private void crearUsuario(){
        Boolean passAceptado = this.validacion(txt_password.getText().toString(), txt_checkpassword.getText().toString());
        if(passAceptado){
            baseDatos.datosUsuario(
                    txt_nombre.getText().toString(),
                    txt_email.getText().toString(),
                    txt_telefono.getText().toString(),
                    txt_direccion.getText().toString(),
                    txt_usuario.getText().toString(),
                    txt_password.getText().toString()
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



    private boolean validacion(String pass1, String pass2){
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

}
