package com.example.edukasikesehatankejiwaan.model.chat

data class ItemMessage (
    var senderId: String = "",
    var receiverId: String = "",
    var message: String = "",
    var urlFoto: String = "",
    var urlFile: String = "",
    var idUser: String = "",
    var idTujuan: String = "",
    var idChat: String = "",
    var timeStamp: Long = 0,
    var status: String = ""
)