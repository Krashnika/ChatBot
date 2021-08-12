package com.example.chatbot.ui

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatbot.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_remind.*
import java.time.ZonedDateTime.from

class Remind : AppCompatActivity() {

    lateinit var notificationManager :NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder:Notification.Builder
    var channelId  ="com.example.chatbot.ui"
    var description =  "Its time!!"
    lateinit var  alarmManager:AlarmManager
    lateinit var context :Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remind)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        btnshow.setOnClickListener {

            val intent = Intent(applicationContext,Remind::class.java)
            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val timebtnclick=System.currentTimeMillis()

            val sec =1000*10



            alarmManager.set(AlarmManager.RTC_WAKEUP,timebtnclick+sec,pendingIntent)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){




            notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder =  Notification.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("New Message")
                .setCategory(this.alarmManager.toString())
                .setContentIntent(pendingIntent)

        }}



        notificationManager.notify(200, this.builder.build())




    }
}