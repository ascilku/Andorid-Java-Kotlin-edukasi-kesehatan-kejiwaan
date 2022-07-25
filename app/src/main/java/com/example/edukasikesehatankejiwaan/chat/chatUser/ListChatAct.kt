package com.example.edukasikesehatankejiwaan.chat.chatUser

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.chat.ListUserAct
import com.example.edukasikesehatankejiwaan.databinding.ActivityListChatBinding
import com.example.edukasikesehatankejiwaan.fcm.*
import com.example.edukasikesehatankejiwaan.fcmNetwork.FcmNetwork
import com.example.edukasikesehatankejiwaan.model.chat.ItemMessage
import com.example.edukasikesehatankejiwaan.model.chat.ListMessageUser
import com.example.edukasikesehatankejiwaan.utils.Cons.DB
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Response

class ListChatAct : AppCompatActivity() {
    lateinit var b : ActivityListChatBinding
    lateinit var apiService : FcmNetwork
    val list = ArrayList<ItemMessage>()
    lateinit var adapterChat: AdapterChat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityListChatBinding.inflate(layoutInflater)
        setContentView(b.root)

        val bundle = intent.extras
        setAdapterItemChat()
        setListChat()
        apiService = Common.fcmClient()
        setUserProfil(bundle)
        onClick(bundle)
    }
    private fun setUserProfil(bundle: Bundle?) {
        val name = bundle?.getString("hisNama").toString()
        b.textName.text = name
//        val nickName  = bundle?.getString("hisNickName").toString()
//        val image = bundle?.getString("hisImage").toString()
//        if (image == ""){
//            Glide.with(b.imgFoto)
//                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
//                .load(R.drawable.ic_person)
//                .into(b.imgFoto)
//        }else{
//            Glide.with(b.imgFoto)
//                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
//                .load(image)
//                .into(b.imgFoto)
//        }
//        if (nickName != ""){
//            b.textName.text = nickName
//        }else{
//            b.textName.text = name
//        }
    }

    private fun onClick(bundle: Bundle?) {
        DB.child("Tokens").child(bundle?.getString("hisId").toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val tokens = snapshot.getValue(Token::class.java)
                        val token = tokens?.token.toString()
                        b.imgSend.setOnClickListener {
                            val data = Notif(
                                b.edtPesan.text.toString(),
                                bundle?.getString("MyNama").toString()
                            )
                            val sender = Sender(data, token)
                            apiService.sendNotification(sender).enqueue(object : retrofit2.Callback<Respond>{
                                override fun onFailure(call: Call<Respond>, t: Throwable) {
                                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                                }
                                override fun onResponse(call: Call<Respond>, response: Response<Respond>) {
                                    if (response.body()?.succes == 1) {
                                        Log.e("Success" , response.message().toString())
                                    }else{
                                        setInsertData(bundle)
                                    }
                                }
                            })
                        }
                    }
                    else{
                        b.imgSend.setOnClickListener{
                            Toast.makeText(applicationContext,
                                "tidak dapat mengirim chat karena tujuan belum melengkapi data", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun setInsertData(bundle: Bundle?) {
        bundle?.getString("hisNama")
        bundle?.getString("hisId")
        bundle?.getString("MyNama")
        bundle?.getString("MyId")
        val myMessageId = bundle?.getString("MyId").toString() + bundle?.getString("hisId").toString()
        val hisMessageId = bundle?.getString("hisId").toString() + bundle?.getString("MyId").toString()
        val timestamp = System.currentTimeMillis() / 1000

        //My
        val idListChat = DB.push().key.toString()
        val myListUsrChat = ListMessageUser(
            bundle?.getString("MyId").toString(),
            bundle?.getString("hisId").toString(),
            idListChat,
            "",
            "",
            "",
            "",
            b.edtPesan.text.toString(),
            0,
            bundle?.getString("hisNama").toString(),
            bundle?.getString("hisImage").toString(),
            "read"
        )
        DB.child("Chat")
            .child("ListUser")
            .child(bundle?.getString("MyId").toString())
            .child(bundle?.getString("hisId").toString())
            .setValue(myListUsrChat)
            .addOnCompleteListener {
                b.edtPesan.setText("")
            }

        val idChat = DB.push().key.toString()
        val myListMessage = ItemMessage(
            "",
            "receiverId",
            b.edtPesan.text.toString(),
            "urlFoto",
            "urlFile",
            "idUser",
            "",
            idChat,
            timestamp,
            "Me"
        )
        DB.child("Chat")
            .child("ListChat")
            .child(myMessageId)
            .child(idChat)
            .setValue(myListMessage)

        //His
        val hisIdListChat = DB.push().key.toString()
        val hisListUsrChat = ListMessageUser(
            bundle?.getString("hisId").toString(),
            bundle?.getString("MyId").toString(),
            hisIdListChat,
            "",
            "",
            "",
            "",
            b.edtPesan.text.toString(),
            0,
            bundle?.getString("MyNama").toString(),
            bundle?.getString("MyImage").toString()
        )
        DB.child("Chat")
            .child("ListUser")
            .child(bundle?.getString("hisId").toString())
            .child(bundle?.getString("MyId").toString())
            .setValue(hisListUsrChat)

        val hisIdChat = DB.push().key.toString()

        val hisListMessage = ItemMessage(
            "",
            "receiverId",
            b.edtPesan.text.toString(),
            "urlFoto",
            "urlFile",
            "idUser",
            "",
            hisIdChat,
            timestamp,
            "He"
        )
        DB.child("Chat")
            .child("ListChat")
            .child(hisMessageId)
            .child(hisIdChat)
            .setValue(hisListMessage)

    }

    fun setAdapterItemChat(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        b.rvChat.layoutManager = layoutManager
        adapterChat = AdapterChat(list
        ) { listPosting: ItemMessage -> onItemClick(listPosting) }
        b.rvChat.setHasFixedSize(true)
    }

    private fun onItemClick(listPosting: ItemMessage) {

    }

    fun setListChat(){
        val bundle = intent.extras
        val myMessageId = bundle?.getString("MyId").toString() + bundle?.getString("hisId").toString()
        DB.child("Chat").child("ListChat")
            .child(myMessageId)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()
                }
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()){
                        list.clear()
                        for (snap in p0.children){
                            val listUser = snap.getValue(ItemMessage::class.java)
                            try {
                                adapterChat.notifyDataSetChanged()
                                list.add(listUser!!)
                                b.rvChat.adapter = adapterChat
                                adapterChat.notifyItemInserted(list.size)
                                b.rvChat.smoothScrollToPosition(list.size)
                            }catch (e : Exception){
                                Toast.makeText(applicationContext,e.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

            })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ListUserAct::class.java))
        finish()
    }

}