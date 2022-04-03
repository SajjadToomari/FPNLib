package com.example.pushnotificationservicejava;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.Build.VERSION;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.Map;

public abstract class FCMService extends FirebaseMessagingService {
    public void onNewToken(String p0) {

        Log.d("Firebase", "NewToken");
    }

    public void onMessageReceived(final RemoteMessage remoteMessage) {
        remoteMessage.getData();
        Map var10001 = remoteMessage.getData();
        this.onNotificationReceived(var10001);


        try {
            Log.d("Firebase", "NewMessage");
            remoteMessage.getData();
            PushNotification.INSTANCE.event((Function1)(new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((Map)var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull Map it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    remoteMessage.getData();
                }
            }));
            String title = (String)remoteMessage.getData().get("title");
            String text = (String)remoteMessage.getData().get("text");
            Object var10000;
            if (VERSION.SDK_INT >= 26) {
                String name = "Notification";
                String descriptionText = "Description";
                int importance = 4;
                NotificationChannel var8 = new NotificationChannel("MainActivity", (CharSequence)name, importance);
                boolean var9 = false;
                boolean var10 = false;
                var8.setDescription(descriptionText);
                NotificationChannel channel = var8;
                var8.setLightColor(-7829368);
                var8.enableLights(true);
                var10000 = this.getSystemService("notification");
                if (var10000 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.app.NotificationManager");
                }

                NotificationManager notificationManager = (NotificationManager)var10000;
                notificationManager.createNotificationChannel(channel);
            }

            Notification builder = (new Builder((Context)this, "MainActivity")).setSmallIcon(drawable.download).setContentTitle((CharSequence)title).setContentText((CharSequence)text).setPriority(1).setAutoCancel(true).build();
            NotificationManagerCompat var23 = NotificationManagerCompat.from((Context)this);
            Intrinsics.checkNotNullExpressionValue(var23, "NotificationManagerCompat.from(this)");
            NotificationManagerCompat notificationManager = var23;
            notificationManager.notify(100, builder);
            Uri var24 = RingtoneManager.getDefaultUri(2);
            Intrinsics.checkNotNullExpressionValue(var24, "RingtoneManager.getDefau…anager.TYPE_NOTIFICATION)");
            Uri notification = var24;
            Ringtone r = RingtoneManager.getRingtone(this.getApplicationContext(), notification);
            r.play();
            Context var25 = this.getApplicationContext();
            var10000 = var25 != null ? var25.getSystemService("vibrator") : null;
            if (var10000 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.os.Vibrator");
            }

            Vibrator vibrator = (Vibrator)var10000;
            if (VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200L, -1));
            } else {
                vibrator.vibrate(200L);
            }
        } catch (Exception var13) {
            CharSequence var3 = (CharSequence)var13.getMessage();
            boolean var4 = false;
            boolean var5 = false;
            Log.e("Firebase", var3 == null || var3.length() == 0 ? "" : String.valueOf(var13.getMessage()));
        }

    }

    public abstract void onNotificationReceived(@NotNull Map var1);
}
