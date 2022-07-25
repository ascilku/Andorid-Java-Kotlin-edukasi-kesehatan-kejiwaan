package com.example.edukasikesehatankejiwaan.fcm

import com.google.firebase.database.Exclude

data class AppliedUser(
    @Exclude val id: String = "",
    val idUser: String = "",
    val grade: String = "",
    val status : String = "",
    val nama : String? = ""
)