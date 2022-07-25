package com.example.edukasikesehatankejiwaan.chat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.chat.chatUser.ListChatAct
import com.example.edukasikesehatankejiwaan.chat.search.SearchAct
import com.example.edukasikesehatankejiwaan.databinding.ActivityChatBinding
import com.example.edukasikesehatankejiwaan.model.DataUser
import com.example.edukasikesehatankejiwaan.model.chat.ListMessageUser
import com.example.edukasikesehatankejiwaan.utils.Cons
import com.example.edukasikesehatankejiwaan.utils.Cons.prefManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ListUserAct : AppCompatActivity() {
    val list = ArrayList<ListMessageUser>()
    lateinit var adapterListChat: AdapterListUser
    lateinit var b : ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityChatBinding.inflate(layoutInflater)
        setContentView(b.root)

        setDataListChat()
        if (prefManager.getUserName(baseContext)?.contains("@gmail.com") == true){
            b.search.visibility = View.VISIBLE
            b.m.visibility = View.GONE
        }else{
            b.m.visibility = View.VISIBLE
            b.search.visibility = View.GONE
        }
        setAdapterListChat()
        b.refResh.isRefreshing = false

        onClick()
    }

    private fun onClick() {
        b.refResh.setOnRefreshListener {
            list.clear()
            setAdapterListChat()
            setDataListChat()
            b.refResh.isRefreshing = false
        }
        b.search.setOnClickListener {
            startActivity(Intent(this, SearchAct::class.java))
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            finish()
        }
    }

    fun setAdapterListChat(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        layoutManager.stackFromEnd = true
        b.rvListChat.layoutManager = layoutManager

        adapterListChat = AdapterListUser(list
        ) { listPosting: ListMessageUser -> onItemClick(listPosting) }
        b.rvListChat.setHasFixedSize(true)
    }

    private fun onItemClick(listPosting: ListMessageUser) {
        val bundle = Bundle()
        val intent = Intent(this, ListChatAct::class.java)
        bundle.putString("hisNama", listPosting.nama)
//                            bundle.putString("hisImage", listPosting.foto)
        bundle.putString("hisId", listPosting.idTujuan)
        bundle.putString("MyNama", prefManager.getUserName(baseContext))
//                            bundle.putString("MyImage", bio.userImage)
        bundle.putString("MyId", prefManager.getUserLink(baseContext))
        intent.putExtras(bundle)
        startActivity(intent)
        finish()

        val myListUsrChat = ListMessageUser(
            listPosting.idUser,
            listPosting.idTujuan,
            listPosting.idChat,
            listPosting.waktu,
            listPosting.tahun,
            listPosting.bulan,
            listPosting.tanggal,
            listPosting.msg,
            listPosting.timeStmp,
            listPosting.nama,
            listPosting.foto,
            "read"
        )
        Cons.DB.child("Chat")
            .child("ListUser")
            .child(listPosting.idUser)
            .child(listPosting.idTujuan)
            .setValue(myListUsrChat)

    }

    fun setDataListChat(){
        Cons.DB.child("Chat").child("ListUser")
            .child(prefManager.getUserLink(baseContext).toString())
            .addValueEventListener(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onCancelled(p0: DatabaseError) {
                    b.rvListChat.visibility = View.GONE
                    b.txtChatBelumAda.visibility =  View.VISIBLE
                    b.txtChatBelumAda.text = "koneksi buruk"
                }
                @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()){
                        b.rvListChat.visibility = View.VISIBLE
                        b.txtChatBelumAda.visibility =  View.GONE
                        list.clear()
                        for (snap in p0.children){
                            val listUser = snap.getValue(ListMessageUser::class.java)
                            try {
                                adapterListChat.notifyDataSetChanged()
                                list.add(listUser!!)
                                b.rvListChat.adapter = adapterListChat
                            }catch (e : Exception){
                                Toast.makeText(applicationContext,e.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        b.rvListChat.visibility = View.GONE
                        b.txtChatBelumAda.visibility =  View.VISIBLE
                        b.txtChatBelumAda.text = "Belum ada chat"
                    }
                }

            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}