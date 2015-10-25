package br.usjt.voo3ASINv1.util;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonSerializer;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.Map;

public class GenericRequest {
    public interface OnRequestResult {
        void OnSuccess(String result);

        void OnError(Throwable t);
    }

    public static void RequestPost(final String url, final Map<String, String> bodyParams, final OnRequestResult callback) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    FormEncodingBuilder encodingBuilder = new FormEncodingBuilder();
                    if (bodyParams != null) {
                        for (Map.Entry<String, String> bodyParam : bodyParams.entrySet()) {
                            encodingBuilder.add(bodyParam.getKey(), bodyParam.getValue());
                        }
                    }

                    Request request = new Request.Builder()
                            .url(url)
                            .post(encodingBuilder.build())
                            .build();

                    Response response = client.newCall(request).execute();
                    String jsonStr = response.body().string();
                    callback.OnSuccess(jsonStr);
                } catch (Exception ex) {
                    callback.OnError(ex);
                }
                return null;
            }
        }.execute();
    }

    public static void RequestGet(final String url, final OnRequestResult callback) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url(url)
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();
                    String jsonStr = response.body().string();
                    callback.OnSuccess(jsonStr);
                } catch (Exception ex) {
                    callback.OnError(ex);
                }
                return null;
            }

        }.execute();
    }
}