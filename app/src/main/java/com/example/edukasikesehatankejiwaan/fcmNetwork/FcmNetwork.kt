package com.example.edukasikesehatankejiwaan.fcmNetwork

import com.example.edukasikesehatankejiwaan.fcm.Respond
import com.example.edukasikesehatankejiwaan.fcm.Sender
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FcmNetwork {
    @Headers(
        "Content-Type:application/json",
        "Authorization:key=AAAAXAL1V0I:APA91bFR1laFDxhtTaJF2Rv_w_3R6_MCglFxaWwcBsaZh0_KLd8rp8JnH28R7NCDDXscwoKAhaGzwyG3XvDhjgM_W0-Du6mJ5R6mN51falUIBcfEsWXashkmBLw_IibZLsRiAOgtjGUl"
    )
    @POST("fcm/send")
    fun sendNotification(@Body body: Sender) : Call<Respond>
}