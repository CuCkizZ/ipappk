package com.example.ipapp.api

import com.example.ipapp.model.MyIP
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("d4e2bt6jba6cmiekqms")
    suspend fun getIP(): Response<MyIP>
}