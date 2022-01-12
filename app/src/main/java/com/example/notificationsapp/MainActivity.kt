package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notificationsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    //private val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    private lateinit var builder: Notification.Builder

    //The channel id and channel description is what the user will see in the settings when they check for the notifications
    private val channelID = "NotificationsApp.notifications"
    private val channelDescription = "Notification App Test"

    //notificationID is an integer id that we can set for the notification,
    //so the notification manager can know which notification we are referring to in case we have multiple notifications.
    private val notificationID = 14

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.displayBtn.setOnClickListener { displayNotification() }

    }


    private fun displayNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(this, NotificationActivity::class.java)

        //Creating a pending intent of the intent we created before, in case the user clicked on the notification.
        //The pending intent will be waiting until the user clicks on the notification.
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        //In order to create a stable notification that works on newer and older versions we need to check the Android version running on the user's device.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {   //Creating a notification channel for the notification.

            //The last argument in the notification channel is the importance of the notification, if it is high, it means that it will make a sound, vibrate, and notify the user even if the phone is silent in some cases
            val notificationChannel = NotificationChannel(
                channelID,
                channelDescription,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)

            //This code shows the part that will be within the “if the current android version is newer or equal to Oreo Android version”
            builder = Notification.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent)
                .setContentTitle("Notification App")
                .setContentText(binding.userInputET.text)

        } else {   //For the older version we can do the same but without using the channel id in the builder.
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent)
                .setContentTitle("Notification App")
                .setContentText(binding.userInputET.text)
        }
        //The final step is to initiate the notification
        notificationManager.notify(notificationID, builder.build())
    }

}



/*
* To Create a notification, we follow these steps:
*
- Create notification manager
- Create notification channel if android version >= Oreo
- Create notification builder with the content of the notification
- Call notification manager notify
*
* */