package com.example.chatbot.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatbot.R

import java.util.*

class alarm : AppCompatActivity() {

    private lateinit var alarmService: AlarmService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmService = AlarmService(this)
                    setAlarm{timeInMillis ->alarmService.setExactAlarm(timeInMillis)}

    }

    private fun setAlarm(callback :(Long) -> Unit){
        Calendar.getInstance().apply {

            this.set(Calendar.SECOND,0)
            this.set(Calendar.MILLISECOND,0)
            DatePickerDialog(
                this@alarm,
                0,
                { _,year,month, day ->
                    this.set(Calendar.YEAR,year)
                    this.set(Calendar.MONTH,month)
                    this.set(Calendar.DAY_OF_MONTH,day)

                    TimePickerDialog(this@alarm,0,
                        {_, hour, minute ->
                            this.set(Calendar.HOUR_OF_DAY,hour)
                            this.set(Calendar.MINUTE,minute)
                            callback(this.timeInMillis)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false

                    ).show()
                },
                this.get(Calendar.YEAR),this.get(Calendar.MONTH),this.get(Calendar.DAY_OF_MONTH)



            ).show()
        }
    }
}