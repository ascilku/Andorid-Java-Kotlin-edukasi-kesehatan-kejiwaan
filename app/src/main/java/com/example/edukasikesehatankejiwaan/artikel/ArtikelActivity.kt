package com.example.edukasikesehatankejiwaan.artikel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.teleponBantuan.TeleponBantuanActivity

class ArtikelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artikel)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}