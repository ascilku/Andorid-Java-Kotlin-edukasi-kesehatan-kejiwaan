package com.example.edukasikesehatankejiwaan.tempat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edukasikesehatankejiwaan.R;
import com.example.edukasikesehatankejiwaan.apiinterface.Api_Interface;
import com.example.edukasikesehatankejiwaan.artikelnew.ValueTambahArtikel;
import com.example.edukasikesehatankejiwaan.lokasi.LokasiKonsultasi;
import com.example.edukasikesehatankejiwaan.lokasi.TambahLokasi;
import com.example.edukasikesehatankejiwaan.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahTempat extends AppCompatActivity {

    EditText tempat, alamat, jam, telepon;
    Button upload;
    String id, s_tempat, s_alamat, s_jam, s_telepon, is_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_tempat);

        tempat = findViewById(R.id.tempat);
        alamat = findViewById(R.id.alamat);
        jam = findViewById(R.id.jam);
        telepon = findViewById(R.id.telepon);

        upload = findViewById(R.id.upload);

        id = getIntent().getStringExtra("id");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_tempat = tempat.getText().toString();
                s_alamat = alamat.getText().toString();
                s_jam = jam.getText().toString();
                s_telepon = telepon.getText().toString();
                is_id = id;


                upload(id, s_tempat, s_alamat, s_jam, s_telepon);
            }
        });

    }

    private void upload(String s_id, String s_tempat, String s_alamat, String s_jam, String s_telepon) {
//        Toast.makeText(TambahTempat.this, s_id+ " " +s_tempat+ " " +s_alamat+ " " +s_jam+ " " +s_telepon,Toast.LENGTH_SHORT).show();
        Api_Interface mApiInterface = Url.getClient().create(Api_Interface.class);
        Call<ValueTambahArtikel> call = mApiInterface.tambahTempat(s_id,s_tempat,s_alamat,s_jam,s_telepon);
        call.enqueue(new Callback<ValueTambahArtikel>() {
            @Override
            public void onResponse(Call<ValueTambahArtikel> call, Response<ValueTambahArtikel> response) {

                if (response.body() != null){
                    String value = response.body().getValue();
                    if (value.equals("1")){
                        Intent home=new Intent(getApplicationContext(), LokasiKonsultasi.class);
                        startActivity(home);
                        finish();
                        Toast.makeText(TambahTempat.this, "Berhasil Input", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TambahTempat.this, "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TambahTempat.this, "Respons Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueTambahArtikel> call, Throwable t) {

            }
        });

    }
}