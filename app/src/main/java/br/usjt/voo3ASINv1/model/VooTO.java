package br.usjt.voo3ASINv1.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VooTO implements Serializable {
    @SerializedName("origem")
    public String Origem;

    @SerializedName("destino")
    public String Destino;

    @SerializedName("dataVoo")
    public String DataVoo;

    @SerializedName("horaVoo")
    public String HoraVoo;

    @SerializedName("valor")
    public String Valor;

    @SerializedName("situacao")
    public String Situacao;
}
