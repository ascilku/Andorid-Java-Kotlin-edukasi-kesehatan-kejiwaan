package com.example.edukasikesehatankejiwaan.newchat;

import java.util.List;

public class ValueSemuaChat {
    String value;
    List<ModelSemuaSiswa> pesan;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ModelSemuaSiswa> getPesan() {
        return pesan;
    }

    public void setPesan(List<ModelSemuaSiswa> pesan) {
        this.pesan = pesan;
    }
}
