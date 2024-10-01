package com.example.ipapp.repository

import com.example.ipapp.api.RetrofitInstants
import com.example.ipapp.model.MyIP
import retrofit2.Response

class Repository {

    suspend fun getIP(): Response<MyIP> {
        return RetrofitInstants.api.getIP()
    }

}