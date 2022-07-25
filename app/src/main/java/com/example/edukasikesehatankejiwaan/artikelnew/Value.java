package com.example.edukasikesehatankejiwaan.artikelnew;

import java.util.List;

public class Value {
    String value;
    List<ModelArtikel> pesan;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ModelArtikel> getPesan() {
        return pesan;
    }

    public void setPesan(List<ModelArtikel> pesan) {
        this.pesan = pesan;
    }
}
