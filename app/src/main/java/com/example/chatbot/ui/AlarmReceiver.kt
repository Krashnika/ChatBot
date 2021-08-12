package com.example.chatbot.ui

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.text.format.DateFormat.format
import androidx.core.app.NotificationCompat
import com.example.chatbot.utils.Constants
import io.karn.notify.Notify


import android.text.format.DateFormat



class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context,intent: Intent) {

        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME,0L)

        when(intent.action){
            Constants.SET_EXACT_ALARM ->{
                buildnotification(context,"New notification","Its Time!!")
            }
        }


    }

    private fun buildnotification(context:Context,title:String,message:String){
      Notify.with(context)
          .content {
              this.title = title
              this.text = message


          }.show()

    }

    private fun convertDate(timeInMillis: Long):String = DateFormat.format("dd/MM/yyyy hh:mm:ss",timeInMillis).toString()
}