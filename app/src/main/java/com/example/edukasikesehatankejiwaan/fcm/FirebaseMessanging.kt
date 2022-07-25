package com.example.edukasikesehatankejiwaan.fcm

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.chat.ListUserAct
import com.example.edukasikesehatankejiwaan.utils.Cons
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


@Suppress("DEPRECATION")
@SuppressLint("Registered")
class FirebaseMessanging : FirebaseMessagingService(){

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
//        val firebaseUser : FirebaseUser? = FirebaseAuth.getInstance().currentUser
//        val fbUser = prefManager.getLink(applicationContext)
//        val refreshToken : String = p0
//        Log.d("tag","token : $p0")
//        if (firebaseUser != null){
            updateToken(p0)
//        }
    }

    private fun updateToken(refreshToken: String) {
        val firebaseUser = Cons.prefManager.getUserLink(baseContext)
//        val firebaseUser : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val databaseRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tokens")
        val token = Token(refreshToken)
        databaseRef.child(firebaseUser.toString()).setValue(token)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val sented : String = p0.data.get("sented").toString()
//        val user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
//        if (user != null && sented.equals(user.uid)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                sendOreoNotification(p0)
            } else {
                sendNotification(p0.notification!!)
            }
//        }
    }

    private fun sendNotification(p0: RemoteMessage.Notification) {
        val intent = Intent(this@FirebaseMessanging, ListUserAct::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSound : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_chat)
            .setContentTitle(p0.title.toString())
            .setContentText(p0.body.toString())
            .setAutoCancel(true)
            .setSound(defaultSound)
            .setContentIntent(pendingIntent)
        builder.priority = Notification.PRIORITY_MAX
//        val notif = NotificationManagerCompat.from(applicationContext)
        val notif = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notif.notify(0, builder.build())
    }

    private fun sendOreoNotification(remoteMessage: RemoteMessage) {
        val user = remoteMessage.data["user"]
//        val icon = remoteMessage.data["icon"]
        val title = remoteMessage.data["title"]
        val body = remoteMessage.data["body"]
        val notification = remoteMessage.notification
        val intent = Intent(this, ListUserAct::class.java)
        val bundle = Bundle()
        bundle.putString("userid", user)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0,
            intent, PendingIntent.FLAG_ONE_SHOT)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val oreoNotification = OreoNotification(this)
        val builder = oreoNotification.getOreoNotification(
            title, body, pendingIntent,
            defaultSound
        )

        oreoNotification.manager().notify(0, builder.build())
    }

}