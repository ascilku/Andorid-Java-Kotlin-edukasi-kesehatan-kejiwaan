package com.example.edukasikesehatankejiwaan.konsul.psikolog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityPsikologBinding
import com.example.edukasikesehatankejiwaan.konsul.AdapterKonsultasi
import com.example.edukasikesehatankejiwaan.konsul.KonsultasiActivity
import com.example.edukasikesehatankejiwaan.konsul.psikiater.DetailPsikiaterActivity
import com.example.edukasikesehatankejiwaan.model.Konsul

class PsikologActivity : AppCompatActivity() {
    lateinit var b : ActivityPsikologBinding
    private val listData = ArrayList<Konsul>()
    private lateinit var adapterKonsul: AdapterKonsultasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        R.layout.activity_psikolog
        b = ActivityPsikologBinding.inflate(layoutInflater)
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
            Konsul("Psikomorfosa",
                "Jl. RSI Faisal XIV No.63, Banta-Bantaeng, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90222",
                "Senin\t10.00–17.00\n" +
                        "Selasa\t10.00–17.00\n" +
                        "Rabu\t10.00–17.00\n" +
                        "Kamis\t10.00–17.00\n" +
                        "Jumat\t10.00–17.00\n" +
                        "Sabtu\t09.00–14.00\n" +
                        "Minggu\tTutup\n",
                "0822-9231-8587"),
            Konsul("LEMBAGA PSIKOLOGI EXCELLENT",
                " Jalan A.P Pettarani Kompleks Ruko Bisnis Centre 3, Blk. C - D No.15, Masale, Kec. Panakkukang, Kota Makassar, Sulawesi Selatan 90222",
                "Senin\t08.00–16.00\n" +
                        "Selasa\t08.00–16.00\n" +
                        "Rabu\t08.00–16.00\n" +
                        "Kamis\t08.00–16.00\n" +
                        "Jumat\t08.00–16.00\n" +
                        "Sabtu\t08.00–12.00\n" +
                        "Minggu\tTutup\n",
                "(0411) 433666"),
            Konsul("Master Biro Psikologi",
                "Jalan Perintis Kemerdekaan VIII No.L14, Tamalanrea Jaya, Kec. Tamalanrea, Kota Makassar, Sulawesi Selatan 90245",
                "Senin\t09.00–17.00\n" +
                        "Selasa\t09.00–17.00\n" +
                        "Rabu\t09.00–17.00\n" +
                        "Kamis\t09.00–17.00\n" +
                        "Jumat\t09.00–17.00\n" +
                        "Sabtu\t09.00–17.00\n" +
                        "Minggu\tTutup\n",
                "0821-8708-9343"),
            Konsul("Biro Psikologi HESLI",
                "Tello Baru, Kec. Panakkukang, Kota Makassar, Sulawesi Selatan 90245",
                "",
                "0852-2838-7136"),
            Konsul("Yayasan Praktek Psikolog Indonesia Cabang Makassar",
                "Jl. Penjernihan Raya Komp. PDAM No.7, Karampuang, Kec. Panakkukang, Kota Makassar, Sulawesi Selatan 90231",
                "Senin\t09.00–17.00\n" +
                        "Selasa\t09.00–17.00\n" +
                        "Rabu\t09.00–17.00\n" +
                        "Kamis\t09.00–17.00\n" +
                        "Jumat\t09.00–17.00\n" +
                        "Sabtu\t09.00–17.00\n" +
                        "Minggu\t09.00–17.00\n",
                ""),
            Konsul("LPPT Widya Prasthya",
                "Jl. RSI Faisal II Perum Griya Modern No.7, Kel, Banta-Bantaeng, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90222",
                "Senin\t08.30–16.00\n" +
                        "Selasa\t08.30–16.00\n" +
                        "Rabu\t08.30–16.00\n" +
                        "Kamis\t08.30–16.00\n" +
                        "Jumat\t08.30–16.00\n" +
                        "Sabtu\tTutup\n" +
                        "Minggu\tTutup\n",
                "0811-4003-225"),
            Konsul("Layanan Psikologi Cognito",
                "Jalan Bumi Raya, Perumahan Bumi Permata Hijau Jl. Sultan Alauddin No.18, Gn. Sari, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90221",
                "Buka 24 jam",
                "0852-5597-2392"),
            Konsul("Equalita Konsultan",
                "Tidung, Rappocini, Tidung, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90222",
                "Senin\t09.00–17.00\n" +
                        "Selasa\t09.00–17.00\n" +
                        "Rabu\t09.00–17.00\n" +
                        "Kamis\t09.00–17.00\n" +
                        "Jumat\t09.00–17.00\n" +
                        "Sabtu\t09.00–17.00\n" +
                        "Minggu\tTutup\n",
                "0853-4377-3884"),
            Konsul("GRHA MARLY",
            "Jalan Monumen Emmy Saelan Kompleks Graha Sari Blok B4-6, Tidung, Gunung Sari, Rappocini, Makassar City, South Sulawesi 90222",
            "Senin\t08.00–17.00\n" +
                    "Selasa\t08.00–17.00\n" +
                    "Rabu\t08.00–17.00\n" +
                    "Kamis\t08.00–17.00\n" +
                    "Jumat\t08.00–17.00\n" +
                    "Sabtu\tTutup\n" +
                    "Minggu\tTutup\n",
            "(0411) 883210"),
            Konsul("Daya Potensia Indonesia",
                "Jl. Raya Pendidikan No.9, Tidung, Rappocini, Makassar City, South Sulawesi 90222",
                "Senin\t09.00–17.00\n" +
                        "Selasa\t09.00–17.00\n" +
                        "Rabu\t09.00–17.00\n" +
                        "Kamis\t09.00–17.00\n" +
                        "Jumat\t09.00–17.00\n" +
                        "Sabtu\tTutup\n" +
                        "Minggu\tTutup\n",
                "0811-430-253"),
            Konsul("Terapi Psikologi Makassar - WA",
                "Jl. Poros Taman Sudiang Indah No.2, Pai, Kec. Biringkanaya, Kota Makassar, Sulawesi Selatan 90243",
                "Senin\t09.00–17.00\n" +
                        "Selasa\t09.00–17.00\n" +
                        "Rabu\t09.00–17.00\n" +
                        "Kamis\t09.00–17.00\n" +
                        "Jumat\t09.00–17.00\n" +
                        "Sabtu\t09.00–17.00\n" +
                        "Minggu\tTutup\n",
                "0882-4756-8533"),
            Konsul("Praktek Psikolog",
                "Jl. Monumen Emmy Saelan No.5, Gn. Sari, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90221",
                "",
                "(0411) 841189"),
            Konsul("Psikologi Excellent",
                "Jl. A. P. Pettarani No.80 E, Masale, Kec. Panakkukang, Kota Makassar, Sulawesi Selatan 90222",
                "",
                "(0411) 433666")
        )
        listData.addAll(data)
        rView.adapter = adapterKonsul
    }

    private fun onClick(konsul: Konsul) {
        val bundle = Bundle()
        val intent = Intent(this, DetailPsikologActivity::class.java)
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