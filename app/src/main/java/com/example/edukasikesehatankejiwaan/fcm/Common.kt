package com.example.edukasikesehatankejiwaan.fcm

import com.example.edukasikesehatankejiwaan.fcmNetwork.FcmNetwork

object Common {
    var currentToken = ""
    private const val baseUrl = "https://fcm.googleapis.com/"
    fun fcmClient(): FcmNetwork {
        return  Client.getClient(baseUrl).create(FcmNetwork::class.java)
    }
}