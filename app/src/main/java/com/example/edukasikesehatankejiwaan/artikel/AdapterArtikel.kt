package com.example.edukasikesehatankejiwaan.artikel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.databinding.ItemKonsulBinding
import com.example.edukasikesehatankejiwaan.databinding.ItemWebBinding
import com.example.edukasikesehatankejiwaan.model.Konsul

class AdapterArtikel (val listKonsul : List<Konsul>,
                      val onClick : (Konsul) -> Unit)
    : RecyclerView.Adapter<AdapterArtikel.KonsulHolder>() {

    inner class KonsulHolder(val itemPetugas : ItemWebBinding) : RecyclerView.ViewHolder(itemPetugas.root){
        fun bindPetugas(mood : Konsul, onClick: (Konsul) -> Unit){
            itemPetugas.nama.text = mood.nama
            itemPetugas.alamat.text = mood.alamat
            itemPetugas.ly.setOnClickListener {
                onClick(mood)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonsulHolder {
        val bind = ItemWebBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)
        return KonsulHolder(bind)
    }

    override fun onBindViewHolder(holder: KonsulHolder, position: Int) {
        holder.bindPetugas(listKonsul[position], onClick)
    }

    override fun getItemCount(): Int = listKonsul.size
}