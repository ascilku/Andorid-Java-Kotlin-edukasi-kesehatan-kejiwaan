package com.example.edukasikesehatankejiwaan.fcm

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.os.Build
import com.example.edukasikesehatankejiwaan.R

@Suppress("DEPRECATION")
class OreoNotification(base: Context?) : ContextWrapper(base) {
    private var notificationManager: NotificationManager? = null

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.enableLights(false)
        channel.enableVibration(true)
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        manager().createNotificationChannel(channel)
    }

    fun manager(): NotificationManager{
        if (notificationManager == null) {
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        return notificationManager as NotificationManager
    }

    @TargetApi(Build.VERSION_CODES.O)

    fun getOreoNotification(
        title: String?, body: String?,
        pendingIntent: PendingIntent?, soundUri: Uri?
    ): Notification.Builder {
        return Notification.Builder(
            applicationContext,
            CHANNEL_ID
        )
            .setContentIntent(pendingIntent)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_chat)
            .setSound(soundUri)
            .setAutoCancel(true)
    }

    companion object {
        private const val CHANNEL_ID = "Alumni"
        private const val CHANNEL_NAME = "Alumni"
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }
}
