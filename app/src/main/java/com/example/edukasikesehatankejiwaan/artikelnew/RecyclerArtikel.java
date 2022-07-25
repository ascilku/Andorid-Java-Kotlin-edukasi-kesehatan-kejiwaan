package com.example.edukasikesehatankejiwaan.artikelnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edukasikesehatankejiwaan.R;
import com.example.edukasikesehatankejiwaan.artikel.WebActivity;
import com.example.edukasikesehatankejiwaan.url.Url;

import java.util.List;

public class RecyclerArtikel extends RecyclerView.Adapter<RecyclerArtikel.ViewHolder> {
    Context context;
    List<ModelArtikel> model_lap_sayas;
    public RecyclerArtikel(Context context, List<ModelArtikel> model_lap_sayas){
        this.context = context;
        this.model_lap_sayas = model_lap_sayas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_artikel,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Url url = new Url();
        ModelArtikel model_lap_saya = model_lap_sayas.get(position);




            holder.waktuLaporan.setText(model_lap_saya.getJudul());

            holder.jenisLaporan.setText(model_lap_saya.getDeksripsi());
            holder.nomor.setText(model_lap_saya.getPengarang());
            holder.link = model_lap_saya.getUrl();
//            holder.id_pengaduan = model_lap_saya.getId_bukti();




//        else if (holder.statuss.equals("Belum Bayar")){
//            holder.waktuLaporan.setVisibility(View.GONE);
//            holder.jenisLaporan.setVisibility(View.GONE);
//            holder.fotoLaporan.setVisibility(View.GONE);
//            holder.waktuLaporan.setVisibility(View.GONE);
//            holder.lokasiLaporan_new.setVisibility(View.GONE);
//            holder.lokasiLaporan_tidak.setVisibility(View.VISIBLE);
//            Toast.makeText(context,holder.statuss, Toast.LENGTH_SHORT).show();
//            holder.lokasiLaporan_tidak.setText("Tidak Ada History Pembayaran");
//        }
    }

    @Override
    public int getItemCount() {
        return model_lap_sayas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView waktuLaporan, jenisLaporan, nomor;
        ImageView fotoLaporan, editLaporanSaya;
        String link;

        public ViewHolder(View itemView) {
            super(itemView);
            waktuLaporan = itemView.findViewById(R.id.nama);
            jenisLaporan = itemView.findViewById(R.id.alamat);
            nomor = itemView.findViewById(R.id.nomor);

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
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("link", link);
            itemView.getContext().startActivity(intent);
            ((Activity)itemView.getContext()).finish();
        }



//        private void showDialog(){
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(itemView.getContext());
//
//            // set title dialog
////            alertDialogBuilder.setTitle("Keluar dari aplikasi?");
//
//            // set pesan dari dialog
//            alertDialogBuilder
//                    .setMessage("Tekan ya untuk save file")
////                    .setIcon(R.mipmap.ic_launcher)
//                    .setCancelable(false)
//                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog,int id) {
//                            // jika tombol diklik, maka akan menutup activity ini
////                            dbhelp.insertData("ini", "itu"
////                                    , "k", "k", "referensi"
////                                    , "m" , "j"
////                            );
//
//                            dbhelp.insertData(istilah.getText().toString(), definisi.getText().toString(), kategori.getText().toString(),
//                                    detail.getText().toString(), referensi , url , tgl
//
//                            );
//
//                            Toast.makeText(context, "Data Berhasil Di Simpan", Toast.LENGTH_LONG).show();
//                        }
//                    })
//                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // jika tombol ini diklik, akan menutup dialog
//                            // dan tidak terjadi apa2
//                            dialog.cancel();
//                        }
//                    });
//
//            // membuat alert dialog dari builder
//            AlertDialog alertDialog = alertDialogBuilder.create();
//
//            // menampilkan alert dialog
//            alertDialog.show();
//        }


    }


}



