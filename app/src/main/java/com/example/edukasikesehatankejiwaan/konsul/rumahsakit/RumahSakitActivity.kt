package com.example.edukasikesehatankejiwaan.konsul.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityRumahSakitBinding
import com.example.edukasikesehatankejiwaan.konsul.AdapterKonsultasi
import com.example.edukasikesehatankejiwaan.konsul.KonsultasiActivity
import com.example.edukasikesehatankejiwaan.konsul.psikiater.DetailPsikiaterActivity
import com.example.edukasikesehatankejiwaan.model.Konsul

class RumahSakitActivity : AppCompatActivity() {
    lateinit var b : ActivityRumahSakitBinding
    private val listData = ArrayList<Konsul>()
    private lateinit var adapterKonsul: AdapterKonsultasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityRumahSakitBinding.inflate(layoutInflater)
        setContentView(b.root)
        val layoutManager = LinearLayoutManager(this)
        b.rView.layoutManager = layoutManager
        adapterKonsul = AdapterKonsultasi(listData) { konsul: Konsul ->
            onClick(
                konsul
            )
        }
        b.rView.setHasFixedSize(true)
        onItemClick()
        setData(b.rView)

        onItemClick()
    }

    private fun setData(rView: RecyclerView) {
        val data = listOf(
            Konsul("Rumah Sakit Khusus Daerah Sulsel ( Rumah Sakit Jiwa Dadi Makassar)",
                "Jl. Lanto Dg. Pasewang, Maricaya Sel., Kec. Mamajang, Kota Makassar, Sulawesi Selatan 90113",
                "Buka 24 jam",
                "(0411) 873120"),
            Konsul("Primaya Hospital Makassar",
                "Jl. Urip Sumoharjo No.43, Karuwisi Utara, Kec. Panakkukang, Kota Makassar, Sulawesi Selatan 90232",
                "Buka 24 jam",
                "(0411) 454567"),
            Konsul("RSUD Sayang Rakyat",
                "Jl. Pahlawan No.1000, Bulurokeng, Biringkanaya, Kota Makassar",
                "",
                "(0411) 3629911"),
            Konsul("RSUP dr. Wahidin Sudirohusodo Makassar (RSWS)",
                "Jl.Perintis Kemerdekaan Km.11, Tamalanrea, Makassar",
                "",
                "(0411) 583333"),
            Konsul("RS Stella Maris",
                "Jalan Somba Opu No 273, Makassar",
                "",
                "(0411) 854341"),
            Konsul("Siloam Hospitals Makassar",
                "Metro Tanjung Bunga Kav. 9 Makassar",
                "",
                "(0411) 3662900")
        )
        listData.addAll(data)
        rView.adapter = adapterKonsul
    }

    private fun onClick(konsul: Konsul) {
        val bundle = Bundle()
        val intent = Intent(this, DetailRumahSakitActivity::class.java)
        bundle.putString("nama",konsul.nama)
        bundle.putString("alamat",konsul.alamat)
        bundle.putString("jam",konsul.jam)
        bundle.putString("tele",konsul.tele)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    private fun onItemClick() {
        b.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, KonsultasiActivity::class.java))
        finish()
    }
}