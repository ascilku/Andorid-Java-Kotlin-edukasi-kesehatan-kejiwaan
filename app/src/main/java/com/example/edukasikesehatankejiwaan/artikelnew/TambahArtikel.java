package com.example.edukasikesehatankejiwaan.artikelnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edukasikesehatankejiwaan.MainActivity;
import com.example.edukasikesehatankejiwaan.R;
import com.example.edukasikesehatankejiwaan.apiinterface.Api_Interface;
import com.example.edukasikesehatankejiwaan.artikel.ArtikelActivity;
import com.example.edukasikesehatankejiwaan.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahArtikel extends AppCompatActivity {

    EditText judul, deksripsi, pengarang, urll;
    Button upload;
    String s_judul, s_deksripsi, s_pengarang, s_urll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_artikel);

        judul = findViewById(R.id.judul);
        deksripsi = findViewById(R.id.deksripsi);
        pengarang = findViewById(R.id.pengarang);
        urll = findViewById(R.id.urll);
        upload = findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_judul = judul.getText().toString();
                s_deksripsi = deksripsi.getText().toString();
                s_pengarang = pengarang.getText().toString();
                s_urll = urll.getText().toString();
                upload(s_judul, s_deksripsi, s_pengarang, s_urll);
            }
        });


    }

    void upload(String s_judul, String s_deksripsi, String s_pengarang, String s_urll){
//        Toast.makeText(TambahArtikel.this, s_judul+ " " +s_deksripsi+ " " +s_pengarang+ " " +s_urll,Toast.LENGTH_SHORT).show();
        Api_Interface mApiInterface = Url.getClient().create(Api_Interface.class);
        Call<ValueTambahArtikel> call = mApiInterface.tambahArtikel(s_judul,s_deksripsi, s_pengarang, s_urll);
        call.enqueue(new Callback<ValueTambahArtikel>() {
            @Override
            public void onResponse(Call<ValueTambahArtikel> call, Response<ValueTambahArtikel> response) {
                Toast.makeText(TambahArtikel.this, "Berhasil Input", Toast.LENGTH_SHORT).show();

                if (response.body() != null){
                    String value = response.body().getValue();
                    if (value.equals("1")){
                        Intent home=new Intent(getApplicationContext(), Arikel.class);
                        startActivity(home);
                        finish();
                        Toast.makeText(TambahArtikel.this, "Berhasil Input", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TambahArtikel.this, "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TambahArtikel.this, "Respons Tidak Ada", Toast.LENGTH_SHORT).show();
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
        Intent home=new Intent(getApplicationContext(), Arikel.class);
        startActivity(home);
        finish();
    }
}