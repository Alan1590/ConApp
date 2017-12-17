package com.conapp.alangon.conapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.conapp.alangon.basedatos.TrabajoBaseDatosLogeoUsuario;
import com.conapp.alangon.basedatos.modelo.OdooBaseDatosInvoices;
import com.conapp.alangon.personalizaciones.FiltrosParaOdoo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    /*************LISTA VARIABLES******************/
    private EditText txt_usuario, txt_password;
    private String idResult;
    /*************LISTA VARIABLES******************/

    public void setIdResult(String idResult) {
        this.idResult = idResult;
        Log.e("RRRRRRRRRRsss",String.valueOf(idResult));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buton_logeo = (Button) findViewById(R.id.buttonMain_aceptar);
        txt_usuario = (EditText) findViewById(R.id.editTextMain_user);
        txt_password = (EditText) findViewById(R.id.editTextMain_password);
        buton_logeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              logeo(txt_usuario.getText().toString(),txt_password.getText().toString());
            }

        });
        FiltrosParaOdoo filtro = new FiltrosParaOdoo(1);
        filtro.addFiltro(0,"partner_id","like","Centro");

        OdooBaseDatosInvoices xmlRpe = new OdooBaseDatosInvoices("",
                "","","",1);
        xmlRpe.selectModel(2);
        xmlRpe.setFiltros(filtro.getFiltro());
        xmlRpe.setMapeoCampos(filtro.getMapeoCampos());
        xmlRpe.execute();
        try {
            for(int i = 0; i<xmlRpe.get().length;i++){
                Log.e("ERRRORRRRRR1",String.valueOf(xmlRpe.get()[i]));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    protected void logeo(String user, String password){
        TrabajoBaseDatosLogeoUsuario logeoUsuario = new TrabajoBaseDatosLogeoUsuario(this);
        logeoUsuario.execute(user,password, getDeviceId());
        try{
            boolean logeado = logeoUsuario.get();
            if(logeado){
            }
            Intent intLogeo = new Intent(this, ActividadPrincipal.class);
            startActivity(intLogeo);

        }catch (InterruptedException | ExecutionException ex){
            Log.e("ERRROR", ex.getMessage());
        }


    }

    private String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId="";
        try{
             deviceId = tm.getDeviceId();
        }catch (SecurityException ex){
            Log.e("ERRROR", ex.getMessage());
        }
        return deviceId;
    }

    public void registrarNuevoUsuario(View v){


        Intent intNuevoUsuario = new Intent(this,Nuevo_usuarioActivity.class);
        startActivity(intNuevoUsuario);
    }
    }
