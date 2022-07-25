package com.example.edukasikesehatankejiwaan.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ItemListChatBinding
import com.example.edukasikesehatankejiwaan.model.chat.ListMessageUser

class AdapterListUser (val list : List<ListMessageUser>, val onClick : (ListMessageUser) -> Unit)
    : RecyclerView.Adapter<AdapterListUser.ListChatHolder>(){
    inner class ListChatHolder(val item: ItemListChatBinding) : RecyclerView.ViewHolder(item.root){
        fun bind(listMessage : ListMessageUser, onClick: (ListMessageUser) -> Unit){
            if (listMessage.foto == ""){
                Glide.with(item.imgFoto)
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(R.drawable.ic_person)
                    .into(item.imgFoto)
            }else{
                Glide.with(item.imgFoto)
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(listMessage.foto)
                    .into(item.imgFoto)
            }

            if (listMessage.status == "read"){
                item.imgStatus.visibility = View.GONE
            }
            item.textName.text = listMessage.nama
            item.textMessage.text = listMessage.msg
            item.ly.setOnClickListener {
                onClick(listMessage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListChatHolder {
        val bind = ItemListChatBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)

        return ListChatHolder(bind)
    }

    override fun onBindViewHolder(holder: ListChatHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size
}