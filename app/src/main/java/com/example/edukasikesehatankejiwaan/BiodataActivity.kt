package com.example.edukasikesehatankejiwaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.edukasikesehatankejiwaan.databinding.ActivityBiodataBinding
import com.example.edukasikesehatankejiwaan.utils.Cons.prefManager

class BiodataActivity : AppCompatActivity() {
    lateinit var b : ActivityBiodataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityBiodataBinding.inflate(layoutInflater)
        setContentView(b.root)

        onClick()
    }

    override fun onStart() {
        super.onStart()
        if (prefManager.getStatusLog(baseContext)){
            startActivity(Intent(this, SplashScren::class.java))
            finish()
        }
    }
    private fun onClick() {
        b.masuk.setOnClickListener {
            if (b.edtName.text.toString() == ""){
                Toast.makeText(applicationContext, "harap masukkan email", Toast.LENGTH_SHORT).show()
                b.edtName.setText("")
            }
            else if (!b.edtName.text.toString().contains("@gmail.com")){
                Toast.makeText(applicationContext, "harap masukkan alamat email yang valid", Toast.LENGTH_SHORT).show()
                b.edtName.setText("")
            }
            else if (b.edtName.text.toString().contains("@gmail.com")){
                prefManager.setStatusLog(baseContext, true)
                prefManager.setUserName(baseContext, b.edtName.text.toString())
                startActivity(Intent(this, SplashScren::class.java))
                finish()
            }
        }
    }
}