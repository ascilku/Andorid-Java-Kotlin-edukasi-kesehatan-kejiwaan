package com.example.edukasikesehatankejiwaan.model.chat

data class ListMessageUser(
    var idUser: String = "",
    var idTujuan: String = "",
    var idChat: String = "",
    var waktu: String = "",
    var tahun: String = "",
    var bulan: String = "",
    var tanggal: String = "",
    var msg: String = "",
    var timeStmp: Long = 0,
    var nama: String = "",
    var foto: String = "",
    var status: String = ""
)
