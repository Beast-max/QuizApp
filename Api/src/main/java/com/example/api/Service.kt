package com.example.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    @GET("api.php?amount=10")
    fun getQuestions(): Call<Model>
}