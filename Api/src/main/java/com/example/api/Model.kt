package com.example.api


import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val results: List<Result>
)