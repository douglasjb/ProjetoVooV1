package br.usjt.voo3ASINv1.network;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import br.usjt.voo3ASINv1.R;
import br.usjt.voo3ASINv1.util.GenericRequest;

public class AeroportoRequester {
    public static void getAeroportos(Context context, GenericRequest.OnRequestResult callback) {
        String url = context.getString(R.string.urlBaseServidor) + "aeroportos.json";
        GenericRequest.RequestGet(url, callback);
    }

    public static void getVoos(Context context, String origem, String destino, GenericRequest.OnRequestResult callback) {
        String url = context.getString(R.string.urlBaseServidor) + "voos.json";
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("origem", String.valueOf(origem));
        bodyParams.put("destino", String.valueOf(destino));

        GenericRequest.RequestPost(url, bodyParams, callback);
    }
}