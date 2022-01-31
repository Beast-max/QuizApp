package com.example.quizapp.REpo

import com.example.api.ApiClient

object Repo {
    val api = ApiClient.api
    suspend fun getquestion(cart:Int,Diff:String) =api.getQuestions(cart,Diff)
}