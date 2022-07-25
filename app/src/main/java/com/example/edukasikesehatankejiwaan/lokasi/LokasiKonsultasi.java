package com.example.edukasikesehatankejiwaan.lokasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.edukasikesehatankejiwaan.MainActivity;
import com.example.edukasikesehatankejiwaan.R;
import com.example.edukasikesehatankejiwaan.apiinterface.Api_Interface;
import com.example.edukasikesehatankejiwaan.artikelnew.ModelArtikel;
import com.example.edukasikesehatankejiwaan.artikelnew.RecyclerArtikel;
import com.example.edukasikesehatankejiwaan.artikelnew.TambahArtikel;
import com.example.edukasikesehatankejiwaan.artikelnew.Value;
import com.example.edukasikesehatankejiwaan.session.Session_akun;
import com.example.edukasikesehatankejiwaan.url.Url;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LokasiKonsultasi extends AppCompatActivity {

    private List<ModelLokasi> model_lap_sayas = new ArrayList<>();
    ProgressBar progresHome1;
    RecyclerView rv_laporan_saya;
    private RecylerLokasi laporan_adapter;
    FloatingActionButton fab;

    Session_akun session_akun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lokasi_konsultasi);

        session_akun = new Session_akun(this);

        rv_laporan_saya= findViewById(R.id.rv_laporan_saya);
        fab= findViewById(R.id.fab);
        progresHome1= findViewById(R.id.progresHome1);

        laporan_adapter = new RecylerLokasi(this,model_lap_sayas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_laporan_saya.setLayoutManager(mLayoutManager);
        rv_laporan_saya.setItemAnimator(new DefaultItemAnimator());
        rv_laporan_saya.setAdapter(laporan_adapter);
        String status = session_akun.getIdpassword();
        if (status.equals("Siswa")  || status.equals("Guru")){
//            Toast.makeText(getApplicationContext(), "sd", Toast.LENGTH_SHORT).show();
            fab.setVisibility(View.INVISIBLE);
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(getApplicationContext(), TambahLokasi.class);
                startActivity(home);
                finish();
            }
        });

        jadwalSidang();

    }

    void jadwalSidang(){

        Api_Interface mApiInterface = Url.getClient().create(Api_Interface.class);
        Call<ValueLokasi> call = mApiInterface.getLokasi();
        call.enqueue(new Callback<ValueLokasi>() {
            @Override
            public void onResponse(Call<ValueLokasi> call, Response<ValueLokasi> response) {
                assert response.body() != null;
                progresHome1.setVisibility(View.GONE);
                String value = response.body().getValue();
                if (value.equals("1")){
                    model_lap_sayas = response.body().getPesan();
                    laporan_adapter = new RecylerLokasi(getApplicationContext(), model_lap_sayas);
                    rv_laporan_saya.setAdapter(laporan_adapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueLokasi> call, Throwable t) {
                t.printStackTrace();
                progresHome1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(home);
        finish();
    }
}