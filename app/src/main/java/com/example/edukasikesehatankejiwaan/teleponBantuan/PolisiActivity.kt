package com.example.edukasikesehatankejiwaan.teleponBantuan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityPolisiBinding

class PolisiActivity : AppCompatActivity() {
    lateinit var b : ActivityPolisiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPolisiBinding.inflate(layoutInflater)
        setContentView(b.root)
        onClcik()
    }

    private fun onClcik() {
        b.pMakassar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-319277")
            startActivity(intent)
        }
        b.pPanakukang.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-442302")
            startActivity(intent)
        }
        b.pBiringkanaya.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-510108")
            startActivity(intent)
        }
        b.pTallo.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-449329")
            startActivity(intent)
        }
        b.pTamalanrea.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-583504")
            startActivity(intent)
        }
        b.pManggala.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-491100")
            startActivity(intent)
        }
        b.pMariso.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-873753")
            startActivity(intent)
        }
        b.psMakassar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-449345")
            startActivity(intent)
        }
        b.pMamajang.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-872777")
            startActivity(intent)
        }
        b.pBontoala.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-453123")
            startActivity(intent)
        }
        b.pUjungPandang.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-310564")
            startActivity(intent)
        }
        b.pUjungTanah.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-436781")
            startActivity(intent)
        }
        b.pWajo.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-319267")
            startActivity(intent)
        }
        b.pSoekarnoHatta.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-334580")
            startActivity(intent)
        }
        b.pPaotere.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0411-420082")
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, TeleponBantuanActivity::class.java))
        finish()
    }
}