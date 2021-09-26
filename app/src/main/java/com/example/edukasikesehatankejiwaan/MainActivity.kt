package com.example.edukasikesehatankejiwaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edukasikesehatankejiwaan.artikel.ArtikelActivity
import com.example.edukasikesehatankejiwaan.calender.CalenderActivity
import com.example.edukasikesehatankejiwaan.databinding.ActivityMainBinding
import com.example.edukasikesehatankejiwaan.konsul.KonsultasiActivity
import com.example.edukasikesehatankejiwaan.teleponBantuan.TeleponBantuanActivity

class MainActivity : AppCompatActivity() {
    lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        onClick()
    }

    private fun onClick() {
        b.cvCalender.setOnClickListener {
            startActivity(Intent(this, CalenderActivity::class.java))
            finish()
        }

        b.cvArtikel.setOnClickListener {
            startActivity(Intent(this, ArtikelActivity::class.java))
            finish()
        }

        b.cvKonsul.setOnClickListener {
            startActivity(Intent(this, KonsultasiActivity::class.java))
            finish()
        }

        b.cvPhone.setOnClickListener {
            startActivity(Intent(this, TeleponBantuanActivity::class.java))
            finish()
        }
    }
}