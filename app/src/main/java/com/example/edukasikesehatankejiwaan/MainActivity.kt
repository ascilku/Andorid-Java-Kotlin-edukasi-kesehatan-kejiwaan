package com.example.edukasikesehatankejiwaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.edukasikesehatankejiwaan.artikel.ArtikelActivity
import com.example.edukasikesehatankejiwaan.artikelnew.Arikel
import com.example.edukasikesehatankejiwaan.calender.CalenderActivity
import com.example.edukasikesehatankejiwaan.chat.ListUserAct
import com.example.edukasikesehatankejiwaan.databinding.ActivityMainBinding
import com.example.edukasikesehatankejiwaan.fcm.Token
import com.example.edukasikesehatankejiwaan.konsul.KonsultasiActivity
import com.example.edukasikesehatankejiwaan.login.LoginActivity
import com.example.edukasikesehatankejiwaan.lokasi.LokasiKonsultasi
import com.example.edukasikesehatankejiwaan.newchat.ListOrang
import com.example.edukasikesehatankejiwaan.session.Session_akun
import com.example.edukasikesehatankejiwaan.teleponBantuan.TeleponBantuanActivity
import com.example.edukasikesehatankejiwaan.utils.Cons.DB
import com.example.edukasikesehatankejiwaan.utils.Cons.prefManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    var session_akun: Session_akun? = null
    lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        session_akun = Session_akun(this)

        b.akses.text= session_akun!!.getIdpassword()
//        b.konsulBK
        if (session_akun!!.getIdpassword() == "Admin"){
            b.konsulBK.visibility = View.INVISIBLE
        }
        if (prefManager.getUserName(baseContext)?.contains("@gmail.com") == false){
            b.nBk.text = "Message"
        }
        onClick()
    }

    private fun onClick() {
        b.cvCalender.setOnClickListener {
            startActivity(Intent(this, CalenderActivity::class.java))
            finish()
        }

        b.cvArtikel.setOnClickListener {
            startActivity(Intent(this, Arikel::class.java))
//            finish()
        }

        b.cvKonsul.setOnClickListener {
            startActivity(Intent(this, LokasiKonsultasi::class.java))
            finish()
        }

        b.cvPhone.setOnClickListener {
            startActivity(Intent(this, TeleponBantuanActivity::class.java))
            finish()
        }

        b.konsulBK.setOnClickListener {
            startActivity(Intent(this, ListOrang::class.java))
            finish()
        }
        b.logout.setOnClickListener {
            session_akun?.logOut()
            startActivity(Intent(this,  LoginActivity::class.java))
            finish()

        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            updateToken(token)
        })
    }

    private fun updateToken(token: String?) {
        val link = prefManager.getUserLink(baseContext)
        val reference = DB.child("Tokens")
        val tkn = Token(token!!)
        reference.child("$link").setValue(tkn)
            .addOnCompleteListener {
                prefManager.setUserToken(baseContext, token)
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}