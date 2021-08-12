package com.example.chatbot.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.chatbot.utils.Constants
import com.example.chatbot.utils.RandomInUtil

class AlarmService(private val context: Context) {
private val alarmManager:AlarmManager? =
        context.getSystemService(android.content.Context.ALARM_SERVICE) as AlarmManager?

    fun setExactAlarm(timeInMillis: Long ){
        setAlarm(timeInMillis,
            getPendingIntent(getIntent().apply {
                action = Constants.SET_EXACT_ALARM
                putExtra(Constants.EXTRA_EXACT_ALARM_TIME,timeInMillis)


        }))}

    private fun setAlarm(timeInMillis: Long,pendingIntent: PendingIntent){
        alarmManager?.let {
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
            {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent)

            }else{
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent)
            }
        }
    }

    private fun getIntent()=Intent(context,AlarmReceiver::class.java)

    private fun getPendingIntent(intent:Intent) = PendingIntent.getBroadcast(
        context,
        RandomInUtil.getRandomInt(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT)

}