package com.example.edukasikesehatankejiwaan.chat.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.chat.ListUserAct
import com.example.edukasikesehatankejiwaan.chat.chatUser.ListChatAct
import com.example.edukasikesehatankejiwaan.databinding.ActivitySearchBinding
import com.example.edukasikesehatankejiwaan.model.DataUser
import com.example.edukasikesehatankejiwaan.utils.Cons.DB
import com.example.edukasikesehatankejiwaan.utils.Cons.prefManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SearchAct : AppCompatActivity() {
    lateinit var b : ActivitySearchBinding
    val list = ArrayList<DataUser>()
    lateinit var adapterSearch: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(b.root)
        setAdapter()
        setData()
        onSearchUser()
    }
    private fun onSearchUser() {
        b.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()){
                    list.clear()
                    setAdapter()
                    setData()
                }else{
                    adapterSearch.filter.filter(newText)
                }
                return false
            }
        })
        b.search.setOnCloseListener {
            list.clear()
            setAdapter()
            setData()
            false
        }
    }

    fun setAdapter(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        layoutManager.stackFromEnd = true
        b.rvSearchUser.layoutManager = layoutManager

        adapterSearch = SearchAdapter(list
        ) { listPosting: DataUser -> onItemClick(listPosting) }
        b.rvSearchUser.setHasFixedSize(true)
    }

    private fun onItemClick(listPosting: DataUser) {
        val bundle = Bundle()
        val intent = Intent(this, ListChatAct::class.java)
        bundle.putString("hisNama", listPosting.nama)
//                            bundle.putString("hisImage", listPosting.foto)
        bundle.putString("hisId", listPosting.link)
        bundle.putString("MyNama", prefManager.getUserName(baseContext))
//                            bundle.putString("MyImage", bio.userImage)
        bundle.putString("MyId", prefManager.getUserLink(baseContext))
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    fun setData(){
        DB.child("Login")
                .orderByChild("status")
                .equalTo("guru")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()
                    }
                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()){
                            list.clear()
                            for (snap in p0.children){
                                val listUser = snap.getValue(DataUser::class.java)
                                try {
                                    adapterSearch.notifyDataSetChanged()
                                    list.add(listUser!!)
                                    b.rvSearchUser.adapter = adapterSearch
                                }catch (e : Exception){
                                    Toast.makeText(this@SearchAct,e.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else{
                            Toast.makeText(this@SearchAct, "nama pengguna\ntidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
    }

    override fun onStart() {
        super.onStart()
        val imgr: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        b.search.requestFocus()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this , ListUserAct::class.java))
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        finish()
    }
}