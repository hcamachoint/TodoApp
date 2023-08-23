package com.webkingve.todoapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiService {
    @GET("todos/")
    fun getTodo():
            Call<List<TodoResponse>>

    companion object Factory{
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(ApiService::class.java)
        }
    }
}