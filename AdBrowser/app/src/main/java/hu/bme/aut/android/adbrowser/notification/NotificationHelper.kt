package hu.bme.aut.android.adbrowser.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import hu.bme.aut.android.adbrowser.MainActivity
import hu.bme.aut.android.adbrowser.R
import hu.bme.aut.android.adbrowser.model.Ad
import kotlin.random.Random

class NotificationHelper {
    companion object {
        const val AD_ID = "AD_ID"
        const val ACTION_SHOW_AD = "hu.bme.aut.adbrowser.showad"

        fun createNotificationChannels(ctx: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                AdBrowserNotificationChannel.values().forEach {
                    val name = it.channelName
                    val descriptionText = it.channelDescription
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel(it.id, name, importance).apply {
                        description = descriptionText
                    }
                    with(NotificationManagerCompat.from(ctx)) {
                        createNotificationChannel(channel)
                    }
                }

            }
        }

        fun createAdDetailsNotification(ctx: Context, ad: Ad) {
            val intent = Intent(ctx, MainActivity::class.java).apply {
                action = ACTION_SHOW_AD
                putExtra(AD_ID, ad.id)
            }
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val snoozeIntent = Intent(ctx, SnoozeBroadcastReceiver::class.java)
            val snoozePendingIntent: PendingIntent =
                PendingIntent.getBroadcast(ctx, 0, snoozeIntent, 0)

            val builder =
                NotificationCompat.Builder(ctx, AdBrowserNotificationChannel.NEW_ENTRIES.id)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(ad.title)
                    .setContentText(ad.description)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .addAction(
                        0,
                        "N??m??t??s",
                        snoozePendingIntent
                    )
                    .setAutoCancel(true)

            with(NotificationManagerCompat.from(ctx)) {
                notify(Random.Default.nextInt(10000, 100000), builder.build())
            }
        }
        fun createPendingIntentForPromotionNotification(ctx: Context): PendingIntent =
            PendingIntent.getBroadcast(ctx, 0, Intent(ctx, PromoBroadcastReceiver::class.java), 0)

        fun createPromoNotification(ctx: Context) {
            val intent = Intent(ctx, MainActivity::class.java)
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(ctx, 0, intent, 0)

            val builder =
                NotificationCompat.Builder(ctx, AdBrowserNotificationChannel.PROMO.id)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Ne hagyd ki!")
                    .setContentText("Pr??mium el??fizet??s most 50% kedvezm??nnyel")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            with(NotificationManagerCompat.from(ctx)) {
                notify(Random.Default.nextInt(10000, 100000), builder.build())
            }
        }

    }
    fun createPendingIntentForPromotionNotification(ctx: Context): PendingIntent =
        PendingIntent.getBroadcast(ctx, 0, Intent(ctx, PromoBroadcastReceiver::class.java), 0)

    fun createPromoNotification(ctx: Context) {
        val intent = Intent(ctx, MainActivity::class.java)
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(ctx, 0, intent, 0)

        val builder =
            NotificationCompat.Builder(ctx, AdBrowserNotificationChannel.PROMO.id)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Ne hagyd ki!")
                .setContentText("Pr??mium el??fizet??s most 50% kedvezm??nnyel")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(ctx)) {
            notify(Random.Default.nextInt(10000, 100000), builder.build())
        }
    }

}

