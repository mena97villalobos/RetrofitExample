package com.mena97villalobos.retrofitexample.network

import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface TestingApiService {

    @GET("posts")
    suspend fun getAllPosts() : List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

}

object TestingAPI {
    val retrofitService: TestingApiService by lazy {
        retrofit.create(TestingApiService::class.java)
    }
}