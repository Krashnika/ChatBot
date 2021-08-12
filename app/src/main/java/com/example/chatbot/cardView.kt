package com.example.chatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.chatbot.ui.MainActivity
import kotlinx.android.synthetic.main.activity_card_view.*

class cardView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)

        home.setOnClickListener{
            val intent = Intent(this@cardView,MainActivity::class.java)
            startActivity(intent)
        }
        clock.setOnClickListener{
            val intent = Intent(this@cardView,MainActivity::class.java)
            startActivity(intent)
        }
        open_app.setOnClickListener{
            val intent = Intent(this@cardView,MainActivity::class.java)
            startActivity(intent)
        }
        phone.setOnClickListener{
            val intent = Intent(this@cardView,MainActivity::class.java)
            startActivity(intent)
        }
        home.setOnClickListener{
            val intent = Intent(this@cardView,MainActivity::class.java)
            startActivity(intent)
        }
    }
}