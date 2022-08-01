package com.example.edukasikesehatankejiwaan.newchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edukasikesehatankejiwaan.MainActivity;
import com.example.edukasikesehatankejiwaan.R;
import com.example.edukasikesehatankejiwaan.apiinterface.Api_Interface;
import com.example.edukasikesehatankejiwaan.session.Session_akun;
import com.example.edukasikesehatankejiwaan.url.Url;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewChat extends AppCompatActivity {
    String id, nama, chatt;

    private List<ModelSemuaSiswa> model_lap_sayas = new ArrayList<>();
    ProgressBar progresHome1;
    RecyclerView rv_laporan_saya;
    private RecyclerSemuaChat laporan_adapter;
    FloatingActionButton fab;

    Session_akun session_akun;

    LinearLayout send;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_chat);

        id = getIntent().getStringExtra("link");
        nama = getIntent().getStringExtra("nama");
//        Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_SHORT).show();
        TextView textView6= findViewById(R.id.textView6);
        session_akun = new Session_akun(this);
        rv_laporan_saya= findViewById(R.id.rv_laporan_saya);

        send = findViewById(R.id.send);
        editText = findViewById(R.id.editText);

        progresHome1= findViewById(R.id.progresHome1);

        laporan_adapter = new RecyclerSemuaChat(this,model_lap_sayas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_laporan_saya.setLayoutManager(mLayoutManager);
        rv_laporan_saya.setItemAnimator(new DefaultItemAnimator());
        rv_laporan_saya.setAdapter(laporan_adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatt = editText.getText().toString();
                String id_pemilik = session_akun.getId();

                inputChat(chatt, id_pemilik);
            }
        });

        textView6.setText(nama);
        jadwalSidang();
    }
    void inputChat(String chatt, String id_pemilik){
//        Toast.makeText(NewChat.this, id+" "+id_pemilik, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, chatt, Toast.LENGTH_SHORT).show();

        //

//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        Api_Interface mApiInterface = Url.getClient().create(Api_Interface.class);
        Call<ValueInputChat> call = mApiInterface.inputChat(id, id_pemilik, chatt);
        call.enqueue(new Callback<ValueInputChat>() {
            @Override
            public void onResponse(Call<ValueInputChat> call, Response<ValueInputChat> response) {
                assert response.body() != null;
                progresHome1.setVisibility(View.GONE);
                String value = response.body().getValue();
                if (value.equals("1")){

                    Intent home=new Intent(getApplicationContext(), ListOrang.class);
                    startActivity(home);
                    finish();

                    Toast.makeText(NewChat.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueInputChat> call, Throwable t) {
                t.printStackTrace();
                progresHome1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });

    }


    void jadwalSidang(){

//

//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        Api_Interface mApiInterface = Url.getClient().create(Api_Interface.class);
        Call<ValueSemuaChat> call = mApiInterface.getSemuaChat(id);
        call.enqueue(new Callback<ValueSemuaChat>() {
            @Override
            public void onResponse(Call<ValueSemuaChat> call, Response<ValueSemuaChat> response) {
                assert response.body() != null;
                progresHome1.setVisibility(View.GONE);
                String value = response.body().getValue();
                if (value.equals("1")){
                    model_lap_sayas = response.body().getPesan();
                    laporan_adapter = new RecyclerSemuaChat(getApplicationContext(), model_lap_sayas);
                    rv_laporan_saya.setAdapter(laporan_adapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueSemuaChat> call, Throwable t) {
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