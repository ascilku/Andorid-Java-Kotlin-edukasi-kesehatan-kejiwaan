package com.example.edukasikesehatankejiwaan.calender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.databinding.ActivityContentMoodBinding
import com.example.edukasikesehatankejiwaan.model.Mood
import com.example.edukasikesehatankejiwaan.utils.Cons.DB
import com.example.edukasikesehatankejiwaan.utils.Cons.prefManager
import com.example.edukasikesehatankejiwaan.utils.PrefManager

class ContentMoodActivity : AppCompatActivity() {
    lateinit var progress : AlertDialog
    lateinit var inflate : LayoutInflater
    lateinit var builder : AlertDialog.Builder
    lateinit var b : ActivityContentMoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityContentMoodBinding.inflate(layoutInflater)
        setContentView(b.root)

        inflate = layoutInflater
        builder = AlertDialog.Builder(this, R.style.CustomDialog)
        progress = showAlertTest().create()

        onClick()
    }

    private fun onClick() {
        b.imgSatu.setOnClickListener {
            pushMood("satu")
            progress.show()
        }

        b.imgDua.setOnClickListener {
            pushMood("dua")
            progress.show()
        }

        b.imgTiga.setOnClickListener {
            pushMood("tiga")
            progress.show()
        }

        b.imgEmpat.setOnClickListener {
            pushMood("empat")
            progress.show()
        }

        b.imgBack.setOnClickListener {
            onBackPressed()
            progress.show()
        }
    }

    private fun pushMood(emot : String) {
        val idData = DB.push().key.toString()
        val date = intent.extras
        date?.getString("date").toString()
        val name = prefManager.getUserName(baseContext).toString().replace(".",",")
        val data = Mood(
            idData,
            date?.getString("date").toString(),
            b.edtMood.text.toString(),
            emot,
            name
        )

        DB.child("MoodUser/$name").child(idData)
            .setValue(data)
            .addOnCompleteListener {
                progress.dismiss()
                Handler().postDelayed({
                    onBackPressed()
                }, 500)
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "koneksi buruk", Toast.LENGTH_SHORT).show()
            }
    }

    private fun showAlertTest() : AlertDialog.Builder {
        val view = inflate.inflate(R.layout.progress, null)
        builder.setView(view)
        builder.setCancelable(false)
        return builder
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, CalenderActivity::class.java))
        finish()
    }
}