package com.example.chatbot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbot.R
import com.example.chatbot.ui.adapters.RecyclerAdapter
import com.example.chatbot.ui.api.New
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "https://api.currentsapi.services"

 lateinit var countDownTimer:CountDownTimer

private var second = 3L

private var titlesList = mutableListOf<String>()
private var descList = mutableListOf<String>()
private var imagesList = mutableListOf<String>()
private var linksList = mutableListOf<String>()

class News : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news)

        makeAPIRequest()
    }

    private fun setUpRecyclerView(){
        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList, descList, imagesList, linksList)
    }

    private fun addToList(title: String, description: String, image: String, link: String) {
        linksList.add(link)
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    private fun fadeIn() {
        v_blackScreen.animate().apply {
            alpha(0f)
            duration = 3000
        }.start()
    }

    private fun attemptRequestAgain(seconds: Long) {
        countDownTimer = object: CountDownTimer(seconds*1010,1000){
            override fun onFinish() {
                makeAPIRequest()
                countDownTimer.cancel()

            }
            override fun onTick(millisUntilFinished: Long) {
                tv_noInternetCountDown.visibility = View.VISIBLE
                tv_noInternetCountDown.text = "Cannot retrieve data...\nTrying again in: ${millisUntilFinished/1000}"
                Log.d("News", "Could not retrieve data. Trying again in ${millisUntilFinished/1000} seconds")
            }
        }
        countDownTimer.start()
    }

    private fun makeAPIRequest(){
        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)


        GlobalScope.launch ( Dispatchers.IO ){
            try {
                val response = api.getNews()
                for (article in response.news){
                    Log.i("News","Result = $article")
                    addToList(article.title, article.description, article.image, article.url)}
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                    fadeIn()
                    progressBar.visibility = View.GONE
                }


            }catch(e:Exception){
                Log.e("News",e.toString())
                withContext(Dispatchers.Main) {
                    attemptRequestAgain(second)

                }
            }
        }
    }
}