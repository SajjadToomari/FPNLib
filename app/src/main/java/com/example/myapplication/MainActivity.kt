package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pushnotificationservice.EventHandler
import com.example.pushnotificationservice.PushNotification
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val options = FirebaseOptions.Builder()
//            .setProjectId("testpwa-2d83c")
//            .setApplicationId("1:988875451873:android:7d50213ea66ef8039136a0")
//            .setApiKey("AIzaSyBAcXpjfAQS7noZMNiQ9g9l4ZnUNgTE7jA")
//            // .setDatabaseUrl(...)
//            // .setStorageBucket(...)
//            .build()
//
//        Firebase.initialize(this, options)

//        FirebaseApp.initializeApp(this, options)

//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w("", "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result.toString()
//
//            // Log and toast
//            Log.d("", token)
//            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
//        })

        PushNotification().initialize(baseContext);
        EventHandler.getInstance().setListener { object : EventHandler.EventListener {
            override fun OnEvent() {
                TODO("Not yet implemented")
            }

        } }
    }
}