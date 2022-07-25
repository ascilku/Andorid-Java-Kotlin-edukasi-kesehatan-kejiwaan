package com.example.edukasikesehatankejiwaan.fcm

data class Respond (
    var multicast_id: Long = 0,
    var succes: Int = 0,
    var failure: Int = 0,
    var canonical_ids: Int = 0,
    var results: List<Result> = ArrayList()
)