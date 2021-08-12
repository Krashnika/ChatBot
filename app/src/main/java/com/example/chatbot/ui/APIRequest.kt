package com.example.chatbot.ui

import com.example.chatbot.ui.api.NewsApiJSON
import retrofit2.http.GET

interface APIRequest {
    @GET("/v1/search?keywords=Latest&language=en&apiKey=LWqxGp6z1tZiB_nuCD-Ic5Xh-_xcFYdhzvg23tpGIWuuR54N")
    suspend fun getNews() : NewsApiJSON

}