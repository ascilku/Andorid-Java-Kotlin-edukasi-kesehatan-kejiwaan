package com.example.edukasikesehatankejiwaan.teleponBantuan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityTeleponBantuanBinding

class TeleponBantuanActivity : AppCompatActivity() {
    lateinit var b : ActivityTeleponBantuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityTeleponBantuanBinding.inflate(layoutInflater)
        setContentView(b.root)
        onClick()
    }

    private fun onClick() {
        b.sCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:112")
            startActivity(intent)
        }

        b.polis.setOnClickListener {
            startActivity(Intent(this, PolisiActivity::class.java))
            finish()
        }

        b.ambulance.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL)
            i.data = Uri.parse("tel:0411-118")
            startActivity(i)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}