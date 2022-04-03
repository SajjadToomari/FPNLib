package com.example.pushnotificationservice

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class PushNotification {

    fun initialize(context: Context): FirebaseMessaging? {

        val firebase = FirebaseMessaging.getInstance()

        firebase.token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result.toString()

            // Log and toast
            Log.d("FCM", token)
        })

        return firebase;
    }


}