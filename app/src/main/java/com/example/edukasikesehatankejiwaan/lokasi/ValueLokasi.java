package com.example.edukasikesehatankejiwaan.lokasi;

import com.example.edukasikesehatankejiwaan.artikelnew.ModelArtikel;

import java.util.List;

public class ValueLokasi {
    String value;
    List<ModelLokasi> pesan;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ModelLokasi> getPesan() {
        return pesan;
    }

    public void setPesan(List<ModelLokasi> pesan) {
        this.pesan = pesan;
    }
}
