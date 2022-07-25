package com.example.edukasikesehatankejiwaan.tempat;

import com.example.edukasikesehatankejiwaan.artikelnew.ModelArtikel;

import java.util.List;

public class ValueTempat {
    String value;
    List<ModelTempat> pesan;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ModelTempat> getPesan() {
        return pesan;
    }

    public void setPesan(List<ModelTempat> pesan) {
        this.pesan = pesan;
    }
}
