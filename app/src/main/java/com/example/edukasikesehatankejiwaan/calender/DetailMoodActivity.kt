package com.example.edukasikesehatankejiwaan.calender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityDetailMoodBinding

class DetailMoodActivity : AppCompatActivity() {
    lateinit var b : ActivityDetailMoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDetailMoodBinding.inflate(layoutInflater)
        setContentView(b.root)
        val i = intent.extras
        b.dateT.setText(i?.getString("date").toString())
        when(i?.getString("emote").toString()){
            "satu"->{
                b.imgSatu.setImageResource(R.drawable.mukasatu)
            }
            "dua"->{
                b.imgSatu.setImageResource(R.drawable.mukadua)
            }
            "tiga"->{
                b.imgSatu.setImageResource(R.drawable.mukatiga)
            }
            "empat"->{
                b.imgSatu.setImageResource(R.drawable.mukaempat)
            }
        }
        b.edtMood.setText(i?.getString("isi").toString())
        b.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, CalenderActivity::class.java))
        finish()
    }
}