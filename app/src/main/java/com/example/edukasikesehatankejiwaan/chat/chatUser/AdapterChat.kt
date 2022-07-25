package com.example.edukasikesehatankejiwaan.chat.chatUser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.model.chat.ItemMessage

class AdapterChat (val listMessage : List<ItemMessage>,
                   val onClick : (ItemMessage) -> Unit)
    : RecyclerView.Adapter<AdapterChat.ItemChatHolder>()
{
    inner class ItemChatHolder(val items : View) : RecyclerView.ViewHolder(items){
        fun bind(list : ItemMessage, onClick: (ItemMessage) -> Unit){
            if (itemViewType == 1){
                val r  = items.findViewById<AppCompatTextView>(R.id.textMessageMe)
                r.text = list.message
            }else{
                val l  = items.findViewById<AppCompatTextView>(R.id.textMessageOther)
                l.text = list.message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemChatHolder {
        return  if (viewType == 1) {
//            val bRight = ItemChatRightBinding.inflate(
//                LayoutInflater.from(parent.context)
//                , parent, false)

            ItemChatHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_right, parent, false))
        }
        else {
            ItemChatHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_left, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ItemChatHolder, position: Int) {
        holder.bind(listMessage[position], onClick)
    }

    override fun getItemCount(): Int = listMessage.size

    override fun getItemViewType(position: Int): Int {
        return if (listMessage[position].status == "Me") {
            1
        } else {
            2
        }
    }
}