package com.example.edukasikesehatankejiwaan.kontak;

import com.example.edukasikesehatankejiwaan.newchat.ModelUserChat;

import java.util.List;

public class ValueKontak {
    String value;
    List<ModelKontak> pesan;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ModelKontak> getPesan() {
        return pesan;
    }

    public void setPesan(List<ModelKontak> pesan) {
        this.pesan = pesan;
    }
}