package com.mena97villalobos.retrofitexample.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post (
    val userId: Int,
    val id: Int = 0,
    val title: String,
    val body: String
)