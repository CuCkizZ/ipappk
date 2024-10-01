package com.example.ipapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipapp.model.MyIP
import com.example.ipapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<MyIP>> = MutableLiveData()

    fun getIP() {
        viewModelScope.launch {
            val response: Response<MyIP> = repository.getIP()
            myResponse.value = response
        }
    }

}