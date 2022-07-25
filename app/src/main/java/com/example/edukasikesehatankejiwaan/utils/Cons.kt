package com.example.edukasikesehatankejiwaan.utils

import com.google.firebase.database.FirebaseDatabase

object Cons {
    val DB = FirebaseDatabase.getInstance().reference
    val prefManager = PrefManager()
    val kodeUniq = "6020011"
}