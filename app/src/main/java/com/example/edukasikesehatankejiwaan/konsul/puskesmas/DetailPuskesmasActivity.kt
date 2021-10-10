package com.example.edukasikesehatankejiwaan.konsul.puskesmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityDetailPuskesmasBinding
import com.example.edukasikesehatankejiwaan.konsul.KonsultasiActivity

class DetailPuskesmasActivity : AppCompatActivity() {
    lateinit var b : ActivityDetailPuskesmasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDetailPuskesmasBinding.inflate(layoutInflater)
        setContentView(b.root)
        setData()
    }

    private fun setData() {
        val data = intent.extras
        if (data?.getString("jam").toString() == ""){
            b.lyJam.visibility = View.GONE
        }
        if (data?.getString("tele").toString() == ""){
            b.lyTele.visibility = View.GONE
        }
        b.nama.text = data?.getString("nama").toString()
        b.alamat.text = data?.getString("alamat").toString()
        b.jam.text = data?.getString("jam").toString()
        b.tele.text = data?.getString("tele").toString()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, PuskesmasActivity::class.java))
        finish()
    }
}