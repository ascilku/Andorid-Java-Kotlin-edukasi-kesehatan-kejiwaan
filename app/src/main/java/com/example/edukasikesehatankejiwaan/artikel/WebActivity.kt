package com.example.edukasikesehatankejiwaan.artikel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityWebBinding
import android.webkit.WebViewClient
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.artikelnew.Arikel


class WebActivity : AppCompatActivity() {
    lateinit var b : ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityWebBinding.inflate(layoutInflater)
        setContentView(b.root)

        setData()
    }

    private fun setData() {
        val data = intent.extras
        b.artikel.settings.javaScriptEnabled = true
        b.artikel.webViewClient = WebViewClient()
        b.artikel.settings.useWideViewPort = true
        b.artikel.settings.setSupportZoom(true)
        b.artikel.settings.builtInZoomControls = true
        b.artikel.settings.displayZoomControls = false
        b.artikel.loadUrl(data?.getString("link").toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Arikel::class.java))
        finish()
    }
}