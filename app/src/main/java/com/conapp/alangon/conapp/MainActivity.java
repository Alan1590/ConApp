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
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    /*************LISTA VARIABLES******************/
    private EditText txt_usuario, txt_password;
    private String idResult;
    /*************LISTA VARIABLES******************/

    public void setIdResult(String idResult) {
        this.idResult = idResult;
    }

    /**
     * FiltrosParaOdoo filtrosOdoo = new FiltrosParaOdoo(1);
     filtrosOdoo.addFiltro(0,"number","like","0001");
     filtrosOdoo.setCampos(2);
     OdooBaseDatosInvoices invoicesOdoo = new OdooBaseDatosInvoices(this,
     "http://192.168.0.244:8069/xmlrpc/2/object",
     "pruebasOdoo2","admin","admin",1);
     invoicesOdoo.selectModel(2);
     invoicesOdoo.setMapeoCampos(filtrosOdoo.getMapeoCampos());
     invoicesOdoo.setFiltros(filtrosOdoo.getFiltro());
     invoicesOdoo.execute();
     HashMap<String,Object> mapeoResult = new HashMap<>();
     try {
     for(int i =0;i<invoicesOdoo.get().length;i++){
     mapeoResult.putAll((HashMap<String,Object>) invoicesOdoo.get()[i]);
     }

     } catch (InterruptedException e) {
     e.printStackTrace();
     } catch (ExecutionException e) {
     e.printStackTrace();
     }
     * @param savedInstanceState
     */

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

    }

    protected void logeo(String user, String password){
        TrabajoBaseDatosLogeoUsuario logeoUsuario = new TrabajoBaseDatosLogeoUsuario(this);
        logeoUsuario.execute(user,password, getDeviceId());

        try{
            HashMap<String, String> mapeoCampos = new HashMap<>();
            mapeoCampos.putAll(logeoUsuario.get());
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
