package com.example.edukasikesehatankejiwaan.chat.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.model.DataUser
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter (val listUser : ArrayList<DataUser>, val onClick : (DataUser) -> Unit)
    : RecyclerView.Adapter<SearchAdapter.ListUserHolder>(), Filterable {
    private val listUserSearch = listUser
    inner class ListUserHolder(val item : View) : RecyclerView.ViewHolder(item){
        fun bindList(list : DataUser, onClick: (DataUser) -> Unit){
            val n = item.findViewById<AppCompatTextView>(R.id.textName)
            n.text =  list.nama
            item.setOnClickListener {
                onClick(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserHolder {
        return  ListUserHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false))
    }

    override fun onBindViewHolder(holder: ListUserHolder, position: Int) {
        holder.bindList(listUser[position], onClick)
    }

    override fun getItemCount(): Int = listUser.size
    override fun getFilter(): Filter {
        return userFilter
    }
    private val userFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterlist: MutableList<DataUser> = ArrayList()
            if (constraint.isEmpty()) {
                filterlist.addAll(listUserSearch)
            } else {
                val pattrn = constraint.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' }
                for (item in listUserSearch) {
                    if (item.nama.toLowerCase(Locale.ROOT).contains(pattrn) || item.nama.toLowerCase(
                            Locale.ROOT).contains(pattrn)) {
                        filterlist.add(item)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filterlist
            return filterResults
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            listUser.clear()
            listUser.addAll(results.values as ArrayList<DataUser>)
            notifyDataSetChanged()
        }
    }
}