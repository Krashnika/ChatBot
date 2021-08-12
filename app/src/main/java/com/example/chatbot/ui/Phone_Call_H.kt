package com.example.chatbot.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatbot.R
import com.example.chatbot.R.layout.activity_phone__call__h
import kotlinx.android.synthetic.main.activity_phone__call.*
import kotlinx.android.synthetic.main.activity_phone__call__h.*

class Phone_Call_H : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_phone__call__h)
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:102"))
        startActivity(intent)


        }
    }
