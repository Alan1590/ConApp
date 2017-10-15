package com.conapp.alangon.Odoo;

import android.util.Log;
import com.conapp.alangon.Odoo.axmlrpc.*;
import com.conapp.alangon.Odoo.axmlrpc.serializer.StringSerializer;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alan Gon on 13/10/2017.
 */

public class XmlRpcOdoo {
    public void conectar(){
            //XMLRPCClient login = new XMLRPCClient(new URL("https://adm.marinozzi.com.ar/xmlrpc/2/common"), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_CERT);
            //int idCliente = (int) login.callAsync(listener,"login","bd","admin","");
            //XMLRPCClient client = new XMLRPCClient(new URL("url"), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_CERT);
            //LECTURA
            //String[] listParam = {"read"};
            //List<String> parametros = Arrays.asList(listParam);
            //client.callAsync(listener, "execute_kw","bd","uid","pass","model","check_access_rights",parametros);
    }



    /**
     * Logeo de usuario que esta dado por la base de datos principal
     * @param id
     */
    private boolean login(int id){
        return false;
    }

    /**
     * Funcion que devuelve un array de Objectos segun la consulta que se haga en odoo
     * @param model
     * @param filtro
     * @return
     */
    private Object[] filtro(String model, Object[] filtro){
        try{
            XMLRPCClient client = new XMLRPCClient(new URL("url"), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_CERT);
            XMLRPCCallback listener = new XMLRPCCallback() {
            public void onResponse(long id, Object result) {
                // Handling the servers response
                Object[] resultF = (Object[]) result;
                for(int i =0 ; i< resultF.length; i++){
                    Log.e("RESPUETABIEN", String.valueOf(resultF[i]));
                }
            }
            public void onError(long id, XMLRPCException error) {
                // Handling any error in the library
                Log.e("RESPUETAException", error.getMessage());
            }
            public void onServerError(long id, XMLRPCServerException error) {
                // Handling an error response from the server
                Log.e("RESPUETAServerEx", error.getMessage());

            }
        };
        client.callAsync(listener, "execute_kw","prod_v8",1,"rf52*/rf","res.partner","search",
                Arrays.asList(Arrays.asList(
                        Arrays.asList("is_company", "=", true),
                        Arrays.asList("customer", "=", true))));
        }catch(MalformedURLException e){
            Log.e("ASDASDASDASDAS",e.getMessage());
        }
        return null;
    }

}
