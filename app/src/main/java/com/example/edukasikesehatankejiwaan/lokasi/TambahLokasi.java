package com.example.edukasikesehatankejiwaan.lokasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edukasikesehatankejiwaan.R;
import com.example.edukasikesehatankejiwaan.apiinterface.Api_Interface;
import com.example.edukasikesehatankejiwaan.artikelnew.Arikel;
import com.example.edukasikesehatankejiwaan.artikelnew.TambahArtikel;
import com.example.edukasikesehatankejiwaan.artikelnew.ValueTambahArtikel;
import com.example.edukasikesehatankejiwaan.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahLokasi extends AppCompatActivity {

    EditText lokasi;
    Button upload;
    String s_lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_lokasi);
        lokasi = findViewById(R.id.lokasi);
        upload = findViewById(R.id.upload);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_lokasi = lokasi.getText().toString();
                upload(s_lokasi);
            }
        });
    }

    void upload(String s_lokasi){
//        Toast.makeText(TambahArtikel.this, s_judul+ " " +s_deksripsi+ " " +s_pengarang+ " " +s_urll,Toast.LENGTH_SHORT).show();
        Api_Interface mApiInterface = Url.getClient().create(Api_Interface.class);
        Call<ValueTambahArtikel> call = mApiInterface.tambahLokasi(s_lokasi);
        call.enqueue(new Callback<ValueTambahArtikel>() {
            @Override
            public void onResponse(Call<ValueTambahArtikel> call, Response<ValueTambahArtikel> response) {
                Toast.makeText(TambahLokasi.this, "Berhasil Input", Toast.LENGTH_SHORT).show();

                if (response.body() != null){
                    String value = response.body().getValue();
                    if (value.equals("1")){
                        Intent home=new Intent(getApplicationContext(), LokasiKonsultasi.class);
                        startActivity(home);
                        finish();
                        Toast.makeText(TambahLokasi.this, "Berhasil Input", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TambahLokasi.this, "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TambahLokasi.this, "Respons Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueTambahArtikel> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home=new Intent(getApplicationContext(), LokasiKonsultasi.class);
        startActivity(home);
        finish();
    }
}