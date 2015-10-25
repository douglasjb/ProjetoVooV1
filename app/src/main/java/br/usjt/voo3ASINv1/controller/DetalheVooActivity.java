package br.usjt.voo3ASINv1.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.usjt.voo3ASINv1.R;
import br.usjt.voo3ASINv1.model.VooTO;

public class DetalheVooActivity extends AppCompatActivity {
    protected TextView card_voos_origem;
    protected TextView card_voos_destino;
    protected TextView card_voos_data;
    protected TextView card_voos_hora;
    protected TextView card_voos_valor;
    protected TextView card_voos_situacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_voo);

        VooTO current = (VooTO) getIntent().getSerializableExtra("voo");
        setupViews(current);
    }

    private void setupViews(VooTO current) {
        card_voos_origem = (TextView) findViewById(R.id.card_voos_origem);
        card_voos_destino = (TextView) findViewById(R.id.card_voos_destino);
        card_voos_data = (TextView) findViewById(R.id.card_voos_data);
        card_voos_hora = (TextView) findViewById(R.id.card_voos_hora);
        card_voos_valor = (TextView) findViewById(R.id.card_voos_valor);
        card_voos_situacao = (TextView) findViewById(R.id.card_voos_situacao);

        card_voos_origem.setText(current.Origem);
        card_voos_destino.setText(current.Destino);
        card_voos_data.setText(current.DataVoo);
        card_voos_hora.setText(current.HoraVoo);
        card_voos_valor.setText(current.Valor);
        card_voos_situacao.setText(current.Situacao);

    }
}