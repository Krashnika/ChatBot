package com.example.chatbot.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.R
import com.example.chatbot.cardView
import com.example.chatbot.data.Message
import com.example.chatbot.utils.BotResponses
import com.example.chatbot.utils.Constants.CAN_DO
import com.example.chatbot.utils.Constants.NEWS
import com.example.chatbot.utils.Constants.OPEN_CALL
import com.example.chatbot.utils.Constants.OPEN_CALL_H

import com.example.chatbot.utils.Constants.OPEN_GMAIL
import com.example.chatbot.utils.Constants.OPEN_GOOGLE
import com.example.chatbot.utils.Constants.OPEN_MAP
import com.example.chatbot.utils.Constants.OPEN_REMINDER
import com.example.chatbot.utils.Constants.OPEN_SEARCH

import com.example.chatbot.utils.Constants.RECEIVE_ID
import com.example.chatbot.utils.Constants.SEND_ID
import com.example.chatbot.utils.Time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessageAdapter
    private val botlist = listOf("Peter", "Igor", "Francesca")
    private val RQ_SPEECH_REC = 102


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView()

        clickEvents()

        val random = (0..2).random()
        customMessage("Hello! Today you are speaking with ${botlist[random]}, how may I help?")


    }

    private fun clickEvents() {
        btn_send.setOnClickListener {
            sendMessage()
        }

        btn_speak.setOnClickListener {
            askSpeechInput()

        }

        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }


            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val m = result?.get(0).toString()
            et_message.setText(m)


        }

    }

    private fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Speech Recognition is not available", Toast.LENGTH_SHORT).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something")
            startActivityForResult(i, RQ_SPEECH_REC)
        }
    }

    private fun recyclerView() {
        adapter = MessageAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()
        if (message.isNotEmpty()) {
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))

            rv_messages.scrollToPosition(adapter.itemCount - 1)
            botResponse(message)

        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponses.basicResponses(message)
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)

                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                    OPEN_REMINDER -> {
                        val intent = Intent(this@MainActivity, alarm::class.java)
                        startActivity(intent)
                    }
                    OPEN_CALL -> {
                        val intent = Intent(this@MainActivity, Phone_Call::class.java)
                        startActivity(intent)

                    }
                    OPEN_CALL_H -> {
                        val intent = Intent(this@MainActivity, Phone_Call_H::class.java)
                        startActivity(intent)

                    }

                    CAN_DO -> {
                        val intent = Intent(this@MainActivity, cardView::class.java)
                        startActivity(intent)
                    }

                    NEWS -> {
                        val intent = Intent(this@MainActivity, News::class.java)
                        startActivity(intent)

                    }

                    OPEN_GMAIL -> {

                        val intent =
                            packageManager.getLaunchIntentForPackage("com.google.android.gm")
                        if (intent != null) {
                            startActivity(intent)
                        }
                    }

                    OPEN_MAP -> {

                        val intent =
                            packageManager.getLaunchIntentForPackage("com.google.android.apps.maps")
                        if (intent != null) {
                            startActivity(intent)
                        }

                    }

                }

            }
        }

    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)

            }
        }
    }

    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }


        }


    }

    private fun setAlarm() {
        Calendar.getInstance().apply {
            DatePickerDialog(
                this@MainActivity,
                0,
                { _, year, month, day ->
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, day)

                    TimePickerDialog(
                        this@MainActivity, 0,
                        { _, hour, minute ->
                            this.set(Calendar.HOUR_OF_DAY, hour)
                            this.set(Calendar.MINUTE, minute)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false

                    ).show()
                },
                this.get(Calendar.YEAR), this.get(Calendar.MONTH), this.get(Calendar.DAY_OF_MONTH)


            ).show()
        }
    }


}