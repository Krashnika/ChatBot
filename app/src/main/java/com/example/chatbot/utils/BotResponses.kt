package com.example.chatbot.utils

import com.example.chatbot.utils.Constants.CAN_DO
import com.example.chatbot.utils.Constants.NEWS
import com.example.chatbot.utils.Constants.OPEN_CALL
import com.example.chatbot.utils.Constants.OPEN_CALL_H

import com.example.chatbot.utils.Constants.OPEN_GMAIL
import com.example.chatbot.utils.Constants.OPEN_GOOGLE
import com.example.chatbot.utils.Constants.OPEN_MAP
import com.example.chatbot.utils.Constants.OPEN_REMINDER
import com.example.chatbot.utils.Constants.OPEN_SEARCH


object BotResponses {

    fun basicResponses(_message :String) : String {


        val random = (0..2).random()
        val message = _message.toLowerCase()

        return when{

            //Hello
            message.contains("hello") -> {
                when(random){
                    0 -> "Hello there!"
                    1 ->"Hey"
                    2->"Hi"

                 else -> "error"
                }

            }
            message.contains("hi") -> {
                when(random){
                    0 -> "Hello there!"
                    1 ->"Hey"
                    2->"Hi"

                    else -> "error"
                }

            }
            //How are you
            message.contains("how") && message.contains("you") -> {
                when(random){
                    0 -> "I'm doing fine,thanks for asking."

                    1->"Pretty Good! How about you?"

                    else -> "error"
                }

            }
            message.contains("bye") -> {
                when(random){
                    0 -> "Bye!"
                    1 ->"Bye.Come back soon!"
                    2->"Bye.See you soon!"
                    else -> "error"
                }

            }

            message.contains("thank you") -> {
                when(random){
                    0 -> "You're Welcome."
                    1 ->"Happy to help."
                    2->"Welcome!"
                    else -> "error"
                }

            }
            message.contains("thanks") -> {
                when(random){
                    0 -> "You're Welcome."
                    1 ->"Happy to help."
                    2->"Welcome!"
                    else -> "error"
                }

            }

            message.contains("flip") && message.contains("coin") ->{
                var r = (0..1).random()
                val result = if(r==0) "heads" else "tails"

               " I flipped a coin and landed on $result"

            }

            message.contains("time") && message.contains("?") ->{
                Time.timeStamp()
            }
            //Opens google

            message.contains("open") && message.contains("google") ->{
                OPEN_GOOGLE
            }
            message.contains("open") && message.contains("gmail") ->{
                OPEN_GMAIL
            }
            message.contains("find") && message.contains("route") ->{
                OPEN_MAP
            }

            //opens search

            message.contains("search") ->{
                OPEN_SEARCH
            }
            //reminder
            message.contains("remind ") ->{
                OPEN_REMINDER
            }
            message.contains("alarm") ->{
                OPEN_REMINDER
            }
            message.contains("call") && message.contains("police")->{
                OPEN_CALL
            }
            message.contains("call") && message.contains("ambulance")->{
                OPEN_CALL_H
            }
            message.contains("news") ->{
                NEWS

            }
            message.contains("solve") ->{
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }

            }





            else -> {
                when(random){
                    0 -> "I dont understand..."
                    1 ->"I am sorry!I dont know."
                    2->"Try asking me something different!"

                    else -> "error"
                }

            }
        }

    }
}