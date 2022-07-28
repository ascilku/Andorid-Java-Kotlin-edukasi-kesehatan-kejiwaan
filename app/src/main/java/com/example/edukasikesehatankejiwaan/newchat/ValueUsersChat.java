package com.example.edukasikesehatankejiwaan.newchat;

import com.example.edukasikesehatankejiwaan.lokasi.ModelLokasi;

import java.util.List;

public class ValueUsersChat {
    String value;
    List<ModelUserChat> pesan;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ModelUserChat> getPesan() {
        return pesan;
    }

    public void setPesan(List<ModelUserChat> pesan) {
        this.pesan = pesan;
    }
}
