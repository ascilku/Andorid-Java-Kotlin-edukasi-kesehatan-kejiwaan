package com.example.edukasikesehatankejiwaan.artikel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityArtikelBinding
import com.example.edukasikesehatankejiwaan.konsul.AdapterKonsultasi
import com.example.edukasikesehatankejiwaan.model.Konsul
import com.example.edukasikesehatankejiwaan.teleponBantuan.TeleponBantuanActivity

class ArtikelActivity : AppCompatActivity() {
    lateinit var b : ActivityArtikelBinding
    private val listData = ArrayList<Konsul>()
    private lateinit var adapterArtikel: AdapterArtikel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(b.root)
        val layoutManager = LinearLayoutManager(this)
        b.rView.layoutManager = layoutManager
        adapterArtikel = AdapterArtikel(listData) { konsul: Konsul ->
            onClick(
                konsul
            )
        }
        b.rView.setHasFixedSize(true)
        onItemClick()
        setData(b.rView)
    }

    private fun setData(rView: RecyclerView) {
        val data = listOf(
            Konsul("Belajar Mengelola Stres Untuk Hidup Lebih Bahagia",
                "Dyah Ayu Annisa",
                "https://satupersen.net/blog/belajar-mengelola-stress-untuk-hidup-lebih-bahagia",
                ""),
            Konsul("Stres Berat: Penyebab dan Cara Mengatasinya",
                "Dyah Ayu Annisa",
                "https://satupersen.net/blog/stres-berat-penyebab-dan-cara-menghilangkanya",
                ""),
            Konsul("Philophobia: Mengenal Rasa Takut Jatuh Cinta karena Trauma",
                "Dimas Yoga Maha Putra",
                "https://satupersen.net/blog/philophobia-mengenal-rasa-takut-jatuh-cinta-karena-trauma",
                ""),
            Konsul("Tanda-tanda Kamu sedang Kesepian (Cara Bebas dari Rasa Kesepian)",
                "Gabriel Luciana",
                "https://satupersen.net/blog/tanda-tanda-kamu-sedang-kesepian-cara-bebas-dari-rasa-kesepian",
                ""),
            Konsul("Tips Percaya Diri Anti Insecure Paling Ampuh!",
                "Hana Nuralifiah",
                "https://satupersen.net/blog/tips-percaya-diri-anti-insecure-paling-ampuh",
                ""),
            Konsul("Kapan Kita Harus Konsultasi ke Psikolog? (Konseling Online)",
                "Satu Persen",
                "https://satupersen.net/blog/kapan-kita-harus-konsultasi-psikologi-konseling-online",
                ""),
            Konsul("Cara Mengatasi Depresi Secara Mandiri",
                "Rebecca Meliani Sembiring",
                "https://satupersen.net/blog/mengatasi-depresi-di-rumah",
                ""),
            Konsul("Mengenal Apa itu Insecure dan Cara Efektif untuk Mengatasinya",
                "Ade Chandra Gita Kusuma",
                "https://satupersen.net/blog/insecure-mengenal-dan-cara-mengatasinya",
                ""),
            Konsul("Belajar Mengelola Stres Untuk Hidup Lebih Bahagia",
                "Dyah Ayu Annisa",
                "https://satupersen.net/blog/belajar-mengelola-stress-untuk-hidup-lebih-bahagia",
                ""),
            Konsul("5 Makanan Ini Bisa Bantu Kamu Menghilangkan Stres",
                "Ruth Gabriele Tambunan",
                "https://satupersen.net/blog/makanan-menghilangkan-stres",
                ""),
            Konsul("Toxic Positivity: Dampaknya bagi Kesehatan Mental",
                "Hana Nuralifiah",
                "https://satupersen.net/blog/toxic-positivity-dampaknya-bagi-kesehatan-mental",
                ""),
            Konsul("Penyebab Panic Attack bagi Remaja",
                "Nurvidha Qur'aini Suhaemi",
                "https://satupersen.net/blog/penyebab-panic-attack-bagi-remaja",
                ""),
            Konsul("Ketahui Kondisi Mentalmu dengan General Health Questionnaire",
                "Keysha Arum Perdana",
                "https://satupersen.net/blog/ketahui-kondisi-mentalmu-dengan-general-health-questionnaire",
                ""),
            Konsul("5 Hal yang Dirasakan Anak Broken Home",
                "Nouvend Setiawan",
                "https://satupersen.net/blog/5-hal-yang-dirasakan-anak-broken-home",
                ""),
            Konsul("Bahaya Curhat Online di Media Sosial Sembarangan",
                "Hana Nuralifiah",
                "https://satupersen.net/blog/bahaya-curhat-online-medsos",
                ""),
            Konsul("Kenali Gangguan Depresi pada Perempuan",
                "Hana Nuralifiah",
                "https://satupersen.net/blog/penyebab-depresi-pada-perempuan",
                ""),
            Konsul("Arti Overthinking: Penyebab dan Cara Mengatasinya",
                "Fathan Akbar",
                "https://satupersen.net/blog/mengatasi-overthinking",
                ""),
            Konsul("Bingung Mau Curhat ke Siapa",
                "Nida Khairunnisaa",
                "https://satupersen.net/blog/bingung-mau-curhat-ke-siapa",
                ""),
            Konsul("Mengenal Self-Healing dan Cara Menerapkannya di Kehidupan Sehari-hari",
                "Nida Khairunnisaa",
                "https://satupersen.net/blog/mengenal-self-healing",
                ""),
            Konsul("Pengaruh Sosial Media Terhadap Self Esteem: Bikin Bahagia atau Menderita?",
                "Ade Chandra Gita Kusuma",
                "https://satupersen.net/blog/pengaruh-sosial-media-terhadap-self-esteem-bikin-bahagia-atau-menderita",
                ""),
            Konsul("Emosi Itu Bukan Marah! (Mari Mengenal Emosi)",
                "Nouvend Setiawan",
                "https://satupersen.net/blog/emosi-itu-bukan-marah-mari-mengenal-emosi",
                ""),
            Konsul("Self-Love: Menghargai Diri Sendiri, Kalau Bukan Kamu Siapa Lagi?",
                "Ade Chandra Gita Kusuma",
                "https://satupersen.net/blog/self-love-menghargai-diri-sendiri-kalau-bukan-kamu-siapa-lagi",
                ""),
            Konsul("Menghilangkan Rasa Malas : Melalui Video Motivasi",
                "Ade Chandra Gita Kusuma",
                "https://satupersen.net/blog/menghilangkan-rasa-malas-melalui-menonton-video-motivasi",
                ""),
            Konsul("Halu: Kebiasaan Mengkhayal Berlebih dan Cara Menghentikannya",
                "Dyah Ayu Annisa",
                "https://satupersen.net/blog/kebiasaan-mengkhayal-berlebih-dan-cara-menghentikannya",
                ""),
            Konsul("Merasa Hampa dan Kosong? Ketahui Penyebab dan Solusinya",
                "Fathan Akbar",
                "https://satupersen.net/blog/merasa-hampa-dan-kosong-ketahui-penyebab-dan-solusi",
                ""),
            Konsul("Apa Itu Moody dan Bagaimana Cara Mengatasinya",
                "Dyah Ayu Annisa",
                "https://satupersen.net/blog/moody-adalah-hal-buruk-siapa-bilang",
                "")
        )
        listData.addAll(data)
        rView.adapter = adapterArtikel
    }

    private fun onClick(konsul : Konsul) {

        val bundle = Bundle()
        val i = Intent(this, WebActivity::class.java)
        bundle.putString("link", konsul.jam)
        i.putExtras(bundle)
        startActivity(i)
        finish()
    }

    private fun onItemClick() {
        b.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}