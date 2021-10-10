package com.example.edukasikesehatankejiwaan.calender

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ItemMoodBinding
import com.example.edukasikesehatankejiwaan.model.Mood

class AdapterMood(val listMood : List<Mood>,
val onClick : (Mood) -> Unit)
: RecyclerView.Adapter<AdapterMood.MoodHolder>() {

    inner class MoodHolder(val itemPetugas : ItemMoodBinding) : RecyclerView.ViewHolder(itemPetugas.root){
        fun bindPetugas(mood : Mood, onClick: (Mood) -> Unit){
            when(mood.emote){
                "satu"->{
                    itemPetugas.imgEmote.setImageResource(R.drawable.mukasatu)
                }
                "dua"->{
                    itemPetugas.imgEmote.setImageResource(R.drawable.mukadua)
                }
                "tiga"->{
                    itemPetugas.imgEmote.setImageResource(R.drawable.mukatiga)
                }
                "empat"->{
                    itemPetugas.imgEmote.setImageResource(R.drawable.mukaempat)
                }
            }
            itemPetugas.date.text = mood.date
            itemPetugas.content.text = mood.status
            itemPetugas.ly.setOnClickListener {
                onClick(mood)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodHolder {
        val bind = ItemMoodBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)
        return MoodHolder(bind)
    }

    override fun onBindViewHolder(holder: MoodHolder, position: Int) {
        holder.bindPetugas(listMood[position], onClick)
    }

    override fun getItemCount(): Int = listMood.size
}