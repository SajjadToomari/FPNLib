package com.example.pushnotificationservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.net.Uri
import android.os.VibrationEffect
import android.os.Vibrator


class FCMService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        Log.d("Firebase", "NewToken")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        remoteMessage.data
//        onNotificationReceived(remoteMessage.data)

        try {

            Log.d("Firebase", "NewMessage")

            remoteMessage.data

            val title = remoteMessage.data["title"]
            val text = remoteMessage.data["text"]

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Notification"
                val descriptionText = "Description"
                val importance = NotificationManager.IMPORTANCE_HIGH

                val channel = NotificationChannel("MainActivity", name, importance).apply {
                    description = descriptionText
                }

                channel.lightColor = Color.GRAY
                channel.enableLights(true)

                // Register the channel with the system
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(this, "MainActivity")
                .setSmallIcon(R.drawable.download)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()

            val notificationManager = NotificationManagerCompat.from(this)

            notificationManager.notify(100, builder)

            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()

            val vibrator = applicationContext?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }

            EventHandler.getInstance().Test()
        }
        catch (ex: Exception){
            Log.e("Firebase", if (ex.message.isNullOrEmpty()) "" else ex.message.toString())
        }
    }

//    abstract fun onNotificationReceived(data: Map<String, String>)

}