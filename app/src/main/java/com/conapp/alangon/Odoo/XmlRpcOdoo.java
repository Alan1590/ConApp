package com.conapp.alangon.Odoo;

import android.util.Log;
import com.conapp.alangon.Odoo.axmlrpc.*;
import com.conapp.alangon.Odoo.axmlrpc.serializer.StringSerializer;

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
        String[] listParam = {"search"};
        List<String> parametros = Arrays.asList(listParam);
        String[] listBusqueda = {"active","=","1"};
        List<String> parametrosBusqueda = Arrays.asList(listBusqueda);
        Log.e("ERAERASDAS",String.valueOf(parametros));
        try{
            XMLRPCCallback listener = new XMLRPCCallback() {
                public void onResponse(long id, Object result) {
                    // Handling the servers response
                    Log.e("RESPUETA", String.valueOf(result));
                }
                public void onError(long id, XMLRPCException error) {
                    // Handling any error in the library
                    Log.e("RESPUETA", error.getMessage());
                }
                public void onServerError(long id, XMLRPCServerException error) {
                    // Handling an error response from the server
                    Log.e("RESPUETA", error.getMessage());

                }
            };

            XMLRPCClient client = new XMLRPCClient(new URL("https://adm.marinozzi.com.ar/xmlrpc/2/object"), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_CERT);
            client.setLoginData("admin","rf52*/rf");
            //LECTURA
            //String[] listParam = {"read"};
            //List<String> parametros = Arrays.asList(listParam);
            //client.callAsync(listener, "execute_kw","prod_v8","1","rf52*/rf","res.partner","check_access_rights",parametros);
            client.callAsync(listener, "execute","prod_v8","1","rf52*/rf","res.partner","search",parametrosBusqueda);

        }catch(MalformedURLException e){
            Log.e("ASDASDASDASDAS",e.getMessage());
        }

    }

}
