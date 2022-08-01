package com.example.edukasikesehatankejiwaan.newchat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edukasikesehatankejiwaan.R;
import com.example.edukasikesehatankejiwaan.url.Url;

import java.util.List;

public class RecyclerSemuaChat  extends RecyclerView.Adapter<RecyclerSemuaChat.ViewHolder> {
    Context context;
    List<ModelSemuaSiswa> model_lap_sayas;
    public RecyclerSemuaChat(Context context, List<ModelSemuaSiswa> model_lap_sayas){
        this.context = context;
        this.model_lap_sayas = model_lap_sayas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_semua_chat,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Url url = new Url();
        ModelSemuaSiswa model_lap_saya = model_lap_sayas.get(position);



        holder.text.setText(model_lap_saya.getUsername());
        holder.isi.setText(model_lap_saya.getIsi());

        holder.link = model_lap_saya.getId();
        holder.nama = model_lap_saya.getUsername();
//        holder.nomor.setText(model_lap_saya.getStatus());
//        holder.link = model_lap_saya.getUrl();

    }

    @Override
    public int getItemCount() {
        return model_lap_sayas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView isi, text;
        ImageView fotoLaporan, editLaporanSaya;
        String link, nama;

        public ViewHolder(View itemView) {
            super(itemView);
            isi = itemView.findViewById(R.id.isi);
            text = itemView.findViewById(R.id.text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(context, link, Toast.LENGTH_SHORT).show();
            String jenis = link;
//            String definisi1 = definisi.getText().toString();
//            String detail1 = detail.getText().toString();
//            String kategori1 = kategori.getText().toString();




//
            Intent intent = new Intent(context, NewChat.class);
            intent.putExtra("link", link);
            intent.putExtra("nama", nama);
            itemView.getContext().startActivity(intent);
            ((Activity)itemView.getContext()).finish();
        }
    }
}
