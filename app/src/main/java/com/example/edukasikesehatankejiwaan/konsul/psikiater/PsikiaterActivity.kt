package com.example.edukasikesehatankejiwaan.konsul.psikiater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.databinding.ActivityPsikiaterBinding
import com.example.edukasikesehatankejiwaan.konsul.AdapterKonsultasi
import com.example.edukasikesehatankejiwaan.konsul.KonsultasiActivity
import com.example.edukasikesehatankejiwaan.model.Konsul

class PsikiaterActivity : AppCompatActivity() {
    lateinit var b : ActivityPsikiaterBinding
    private val listData = ArrayList<Konsul>()
    private lateinit var adapterKonsul: AdapterKonsultasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPsikiaterBinding.inflate(layoutInflater)
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
            Konsul("Klinik Psikiater Insan Sabir","Jl. Anuang No.16-24, Maricaya, Kec. Makassar, Kota Makassar, Sulawesi Selatan 90142",
                "",
            ""),
            Konsul("Praktek DOKTER PSIKIATER","Jl. Bandang No.291, Parang Layang, Kec. Bontoala, Kota Makassar, Sulawesi Selatan 90155",
                "Senin\t17.00–21.00\n" +
                        "Selasa\t17.00–21.00\n" +
                        "Rabu\t17.00–21.00\n" +
                        "Kamis\t17.00–21.00\n" +
                        "Jumat\t17.00–21.00\n" +
                        "Sabtu\t05.00–21.00\n" +
                        "Minggu\tTutup\n",
            "(0411) 3626109"),
            Konsul("Klinik Praktek Psikiater Fitrah","Jl. Monginsidi No.25, Maricaya Baru, Kec. Makassar, Kota Makassar, Sulawesi Selatan 90141",
                "",
            "0821-8704-0608"),
            Konsul("Sony T Lisal Dr SpKJ","Jl. Irian No.28, Pisang Utara, Kec. Makassar, Kota Makassar, Sulawesi Selatan 90156",
                "",
            "(0411) 3619036"),
            Konsul("Praktek Dokter/Psikiater Dokter Gigi","Jl. DR. Ratulangi No.115, Labuang Baji, Kec. Mamajang, Kota Makassar, Sulawesi Selatan 90132",
                "",
            "(0411) 852763"),
            Konsul("Konsultasi Psikiater","Jl. Rusa, Maricaya, Kec. Makassar, Kota Makassar, Sulawesi Selatan 90142",
                "Senin\t08.00–21.00\n" +
                        "Selasa\t08.00–21.00\n" +
                        "Rabu\t08.00–21.00\n" +
                        "Kamis\t08.00–21.00\n" +
                        "Jumat\t08.00–21.00\n" +
                        "Sabtu\t08.00–21.00\n" +
                        "Minggu\tTutup\n",
            ""),
            Konsul("Psikiater Anak, dr. Rinvil Renaldi, M.Kes, Sp.KJ(K)","Jl. DR. Ratulangi No.115A, Labuang Baji, Kec. Mamajang, Kota Makassar, Sulawesi Selatan 90133",
                "Senin\tTutup\n" +
                        "Selasa\t18.00–21.00\n" +
                        "Rabu\tTutup\n" +
                        "Kamis\t18.00–21.00\n" +
                        "Jumat\tTutup\n" +
                        "Sabtu\t18.00–21.00\n" +
                        "Minggu\tTutup\n",
            "(0411) 852763"),
            Konsul("Praktek Umum Dr. Sonny Tunggal, dr Lili Tunggal Sp.KJ Dan Apotik Prima Husada","Jl. DR. Ratulangi No.180, Bonto Biraeng, Kec. Mamajang, Kota Makassar, Sulawesi Selatan 90125",
                "Senin\t18.30–22.00\n" +
                        "Selasa\t18.30–22.00\n" +
                        "Rabu\t18.30–22.00\n" +
                        "Kamis\t18.30–22.00\n" +
                        "Jumat\t18.30–22.00\n" +
                        "Sabtu\t18.30–22.00\n" +
                        "Minggu\tTutup\n",
            "0852-4444-2630")
        )
        listData.addAll(data)
        rView.adapter = adapterKonsul
    }

    private fun onClick(konsul: Konsul) {
        val bundle = Bundle()
        val intent = Intent(this, DetailPsikiaterActivity::class.java)
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