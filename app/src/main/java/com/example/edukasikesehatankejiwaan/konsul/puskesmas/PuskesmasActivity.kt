package com.example.edukasikesehatankejiwaan.konsul.puskesmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityPuskesmasBinding
import com.example.edukasikesehatankejiwaan.konsul.AdapterKonsultasi
import com.example.edukasikesehatankejiwaan.konsul.KonsultasiActivity
import com.example.edukasikesehatankejiwaan.konsul.psikiater.DetailPsikiaterActivity
import com.example.edukasikesehatankejiwaan.model.Konsul

class PuskesmasActivity : AppCompatActivity() {
    lateinit var b : ActivityPuskesmasBinding
    private val listData = ArrayList<Konsul>()
    private lateinit var adapterKonsul: AdapterKonsultasi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPuskesmasBinding.inflate(layoutInflater)
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
            Konsul("Puskesmas Paccerakkang",
                "Jl. Poros Mangga Tiga, Paccerakkang, Kec. Biringkanaya, Kota Makassar, Sulawesi Selatan 90562",
                "Senin\t08.00–12.00\n" +
                        "Selasa\t08.00–12.00\n" +
                        "Rabu\t08.00–12.00\n" +
                        "Kamis\t08.00–12.00\n" +
                        "Jumat\t08.00–12.00\n" +
                        "Sabtu\t08.00–12.00\n" +
                        "Minggu\tTutup\n",
                ""),
            Konsul("Puskesmas Kassi Kassi",
                "Jl. Tamalate 1 No.43, Kassi-Kassi, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90222",
                "Buka 24 jam",
                "(0411) 863356"),
            Konsul("Puskesmas Tamalanrea",
                "Blok B No.311, Perumahan Bumi Tamalanrea Permai, Jl. Kesejahteraan Tim. I, Tamalanrea, Kec. Tamalanrea, Kota Makassar, Sulawesi Selatan 90245",
                "Senin\t07.30–14.00\n" +
                        "Selasa\t07.30–14.00\n" +
                        "Rabu\t07.30–14.00\n" +
                        "Kamis\t07.30–14.00\n" +
                        "Jumat\t07.30–14.00\n" +
                        "Sabtu\t07.30–14.00\n" +
                        "Minggu\tTutup\n",
                "(0411) 582289"),
            Konsul("Puskesmas Tamangapa",
                "Jl. Tamangapa Raya No.264, Tamangapa, Kec. Manggala, Kota Makassar, Sulawesi Selatan 90235",
                "Tutup ⋅ Buka Sel pukul 08.00",
                ""),
            Konsul("Puskesmas Minasa Upa",
                "Jl. Minasa Upa No.18, Karunrung, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90122",
                "Buka 24 jam",
                ""),
            Konsul("Puskesmas Jongaya",
                "Jl. Andi Tonro No.49, Pa'baeng-Baeng, Kec. Tamalate, Kota Makassar, Sulawesi Selatan 90223",
                "",
                "(0411) 867406"),
            Konsul("Puskesmas Bara Baraya",
                "Jl. Abubakar Lambogo No.143, Bara-Baraya, Kec. Makassar, Kota Makassar, Sulawesi Selatan 90143",
                "Senin\t07.30–14.00\n" +
                        "Selasa\t07.30–14.00\n" +
                        "Rabu\t07.30–14.00\n" +
                        "Kamis\t07.30–14.00\n" +
                        "Jumat\t07.30–11.30\n" +
                        "Sabtu\t07.30–13.00\n" +
                        "Minggu\tBuka 24 jam\n",
                "(0411) 452035"),
            Konsul("Puskesmas Maradekaya",
                "No.27, Jl. Sungai Saddang Baru No.5, Maradekaya Sel., Kec. Makassar, Kota Makassar, Sulawesi Selatan 90142",
                "Senin\t08.00–17.00\n" +
                        "Selasa\t08.00–17.00\n" +
                        "Rabu\t08.00–17.00\n" +
                        "Kamis\t08.00–17.00\n" +
                        "Jumat\t08.00–17.00\n" +
                        "Sabtu\tTutup\n" +
                        "Minggu\tTutup\n",
                ""),
            Konsul("Puskesmas Maccini Sawah",
                "Jl. Maccini Sawah No.38, Maccini, Kec. Makassar, Kota Makassar, Sulawesi Selatan 90232",
                "",
                "(0411) 457307"),
            Konsul("Puskesmas Mamajang",
                "Jl. Drs. Sam Ratulangi, Tamarunang, Kec. Mariso, Kota Makassar, Sulawesi Selatan 90122",
                "Senin\t00.00–18.00\n" +
                        "Selasa\t00.00–18.00\n" +
                        "Rabu\t00.00–18.00\n" +
                        "Kamis\t00.00–18.00\n" +
                        "Jumat\t00.00–18.00\n" +
                        "Sabtu\t00.00–18.00\n" +
                        "Minggu\t00.00–18.00\n",
                "")
        )
        listData.addAll(data)
        rView.adapter = adapterKonsul
    }

    private fun onClick(konsul: Konsul) {
        val bundle = Bundle()
        val intent = Intent(this, DetailPuskesmasActivity::class.java)
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