package com.example.edukasikesehatankejiwaan.kontak;

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
import com.example.edukasikesehatankejiwaan.artikelnew.TambahArtikel;
import com.example.edukasikesehatankejiwaan.newchat.ListOrang;
import com.example.edukasikesehatankejiwaan.newchat.ModelUserChat;
import com.example.edukasikesehatankejiwaan.newchat.RecyclerUserChat;
import com.example.edukasikesehatankejiwaan.newchat.ValueUsersChat;
import com.example.edukasikesehatankejiwaan.session.Session_akun;
import com.example.edukasikesehatankejiwaan.url.Url;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KontakUser extends AppCompatActivity {

    private List<ModelKontak> model_lap_sayas = new ArrayList<>();
    ProgressBar progresHome1;
    RecyclerView rv_laporan_saya;
    private RecyclerKontak laporan_adapter;
    FloatingActionButton fab;

    Session_akun session_akun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kontak_user);

        session_akun = new Session_akun(this);

//        Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();


        rv_laporan_saya= findViewById(R.id.rv_laporan_saya);
        fab= findViewById(R.id.fab);
        progresHome1= findViewById(R.id.progresHome1);

        String status = session_akun.getIdpassword();

//        if (status.equals("Siswa") || status.equals("Guru")){
//            Toast.makeText(getApplicationContext(), "sd", Toast.LENGTH_SHORT).show();
            fab.setVisibility(View.INVISIBLE);
//        }



        laporan_adapter = new RecyclerKontak(this,model_lap_sayas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_laporan_saya.setLayoutManager(mLayoutManager);
        rv_laporan_saya.setItemAnimator(new DefaultItemAnimator());
        rv_laporan_saya.setAdapter(laporan_adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(getApplicationContext(), TambahArtikel.class);
                startActivity(home);
                finish();
            }
        });

        jadwalSidang();

    }

    void jadwalSidang(){

        Api_Interface mApiInterface = Url.getClient().create(Api_Interface.class);
        Call<ValueKontak> call = mApiInterface.getusersChat();
        call.enqueue(new Callback<ValueKontak>() {
            @Override
            public void onResponse(Call<ValueKontak> call, Response<ValueKontak> response) {
                assert response.body() != null;
                progresHome1.setVisibility(View.GONE);
                String value = response.body().getValue();
                if (value.equals("1")){
                    model_lap_sayas = response.body().getPesan();
                    laporan_adapter = new RecyclerKontak(getApplicationContext(), model_lap_sayas);
                    rv_laporan_saya.setAdapter(laporan_adapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueKontak> call, Throwable t) {
                t.printStackTrace();
                progresHome1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home=new Intent(getApplicationContext(), ListOrang.class);
        startActivity(home);
        finish();
    }
}