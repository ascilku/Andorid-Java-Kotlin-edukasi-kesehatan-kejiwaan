package com.example.edukasikesehatankejiwaan.konsul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityKonsultasiBinding
import com.example.edukasikesehatankejiwaan.konsul.psikiater.PsikiaterActivity
import com.example.edukasikesehatankejiwaan.konsul.psikolog.PsikologActivity
import com.example.edukasikesehatankejiwaan.konsul.puskesmas.PuskesmasActivity
import com.example.edukasikesehatankejiwaan.konsul.rumahsakit.RumahSakitActivity

class KonsultasiActivity : AppCompatActivity() {
    lateinit var b : ActivityKonsultasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityKonsultasiBinding.inflate(layoutInflater)
        setContentView(b.root)
        onClick()
    }

    private fun onClick() {
        b.imgBack.setOnClickListener {
            onBackPressed()
        }

        b.puskesmas.setOnClickListener {
            startActivity(Intent(this, PuskesmasActivity::class.java))
            finish()
        }

        b.rmSakit.setOnClickListener {
            startActivity(Intent(this, RumahSakitActivity::class.java))
            finish()
        }

        b.psiko.setOnClickListener {
            startActivity(Intent(this, PsikologActivity::class.java))
            finish()
        }

        b.psiki.setOnClickListener {
            startActivity(Intent(this, PsikiaterActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}