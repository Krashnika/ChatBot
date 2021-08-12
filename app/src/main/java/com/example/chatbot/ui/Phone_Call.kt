package com.example.chatbot.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatbot.R
import kotlinx.android.synthetic.main.activity_phone__call.*

class Phone_Call : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone__call)


            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:100"))
            startActivity(intent)




    }

}