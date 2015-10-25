package br.usjt.voo3ASINv1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.usjt.voo3ASINv1.R;
import br.usjt.voo3ASINv1.model.AeroportoTO;
import br.usjt.voo3ASINv1.network.AeroportoRequester;
import br.usjt.voo3ASINv1.util.CustomAlert;
import br.usjt.voo3ASINv1.util.CustomProgressbar;
import br.usjt.voo3ASINv1.util.GenericRequest;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private Spinner spinnerOrigem;
    private Spinner spinnerDestino;
    private List<AeroportoTO> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        loadAeroportos();
    }

    private void loadAeroportos() {
        CustomProgressbar.show(this);

        AeroportoRequester.getAeroportos(this, new GenericRequest.OnRequestResult() {
            @Override
            public void OnSuccess(final String result) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomProgressbar.hide();

                        Gson gson = new GsonBuilder().create();
                        Type collectionType = new TypeToken<List<AeroportoTO>>() {
                        }.getType();
                        records = gson.fromJson(result, collectionType);

                        List<String> valuesSpinnerOrigem = new ArrayList<>();
                        for (AeroportoTO aeroportoTO : records) {
                            valuesSpinnerOrigem.add(aeroportoTO.Aeroporto);
                        }

                        spinnerOrigem.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, valuesSpinnerOrigem));
                        spinnerDestino.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, valuesSpinnerOrigem));
                    }
                });
            }

            @Override
            public void OnError(final Throwable t) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomProgressbar.hide();
                        CustomAlert.showAlertMessage(MainActivity.this, "Erro", "Ocorreu um erro ao realizar a conexão com o Servidor");
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    private void setupViews() {
        spinnerOrigem = (Spinner) findViewById(R.id.dropdown_origem);
        spinnerDestino = (Spinner) findViewById(R.id.dropdown_destino);
    }

    private int getAeroportoId(String aeroportoNome) {
        for (AeroportoTO aeroportoTO : records) {
            if (aeroportoNome.equalsIgnoreCase(aeroportoTO.Aeroporto)) {
                return aeroportoTO.Id;
            }
        }
        return 0;
    }

    public void consultarVoos(View view) {
        String origem = spinnerOrigem.getSelectedItem().toString();
        String destino = spinnerDestino.getSelectedItem().toString();

        Intent intent = new Intent(MainActivity.this, ListaVooActivity.class);
        intent.putExtra("origem", origem);
        intent.putExtra("destino", destino);

        startActivity(intent);
    }
}