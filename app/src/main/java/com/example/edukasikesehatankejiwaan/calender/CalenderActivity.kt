package com.example.edukasikesehatankejiwaan.calender

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.databinding.ActivityCalenderBinding
import com.example.edukasikesehatankejiwaan.model.Mood
import com.example.edukasikesehatankejiwaan.utils.Cons
import com.example.edukasikesehatankejiwaan.utils.Cons.DB
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CalenderActivity : AppCompatActivity() {
    lateinit var b : ActivityCalenderBinding
    private val listMood = ArrayList<Mood>()
    private lateinit var adapterMood: AdapterMood
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCalenderBinding.inflate(layoutInflater)
        setContentView(b.root)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        b.rvClender.layoutManager = layoutManager
        adapterMood = AdapterMood(listMood) { mood: Mood ->
            onClick(
                mood
            )
        }
        b.rvClender.setHasFixedSize(true)
        onItemClick()
        setData(b.rvClender)
    }

    private fun onClick(mood: Mood) {
        val bundle = Bundle()
        val intent = Intent(this, DetailMoodActivity::class.java)
        bundle.putString("date",mood.date)
        bundle.putString("emote",mood.emote)
        bundle.putString("isi",mood.status)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(rvClender: RecyclerView) {
        val name = Cons.prefManager.getUserName(baseContext).toString().replace(".",",")
        adapterMood.notifyDataSetChanged()
        DB.child("MoodUser/$name").addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    listMood.clear()
                    rvClender.visibility = View.VISIBLE
                    for (snap in snapshot.children){
                        val dataPetugas = snap.getValue(Mood::class.java)
                        listMood.add(dataPetugas!!)
                        rvClender.adapter = adapterMood
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "koneksi buruk", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun onItemClick() {
        val bundle = Bundle()
        val i = Intent(this, ContentMoodActivity::class.java)
        val date  = b.date.dayOfMonth.toString() +"-"+ (b.date.month.plus(1)).toString() +"-"+
                b.date.year.toString()
        bundle.putString("date", date)
        i.putExtras(bundle)
        b.addCalender.setOnClickListener {
            startActivity(i)
            finish()
        }

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