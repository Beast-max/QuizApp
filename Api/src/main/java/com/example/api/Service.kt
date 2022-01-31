package com.example.api


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Service {
    @GET("api.php?amount=10")
   suspend fun getQuestions(@Query("category")category:Int,@Query("difficulty")difficulty:String): Response<Model>
}