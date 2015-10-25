package br.usjt.voo3ASINv1.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.usjt.voo3ASINv1.R;
import br.usjt.voo3ASINv1.adapter.VooAdapter;
import br.usjt.voo3ASINv1.model.VooTO;
import br.usjt.voo3ASINv1.network.AeroportoRequester;
import br.usjt.voo3ASINv1.util.CustomAlert;
import br.usjt.voo3ASINv1.util.CustomProgressbar;
import br.usjt.voo3ASINv1.util.GenericRequest;

public class ListaVooActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_voos);
        recyclerView = (RecyclerView) findViewById(R.id.view_lista_voos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        String origem = getIntent().getStringExtra("origem");
        String destino = getIntent().getStringExtra("destino");

        CustomProgressbar.show(this);
        AeroportoRequester.getVoos(this, origem, destino, new GenericRequest.OnRequestResult() {

            @Override
            public void OnSuccess(final String result) {
                ListaVooActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new GsonBuilder().create();
                        Type collectionType = new TypeToken<List<VooTO>>() {
                        }.getType();

                        List<VooTO> records = gson.fromJson(result, collectionType);
                        setAdapter(records);

                        CustomProgressbar.hide();
                    }
                });
            }

            @Override
            public void OnError(final Throwable t) {
                ListaVooActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomProgressbar.hide();
                        CustomAlert.showAlertMessage(ListaVooActivity.this, "Erro", "Ocorreu um erro ao realizar a conexão com o Servidor");
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    public void setAdapter(final List<VooTO> records) {
        recyclerView.setAdapter(new VooAdapter(this, records));
    }
}