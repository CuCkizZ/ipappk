package com.example.ipapp

import android.icu.util.TimeUnit
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import okhttp3.*
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.ipapp.R.layout.activity_main
import com.example.ipapp.repository.Repository



class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        var textView: TextView = findViewById(R.id.textIp)
        setupNetworkCheckWorker()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getIP()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.myip?.let { myip ->
                    textView.text = myip
                }
            } else {
                textView.text = response.errorBody().toString()
            }

        }
    }

    private fun setupNetworkCheckWorker() {
        val networkCheckRequest = PeriodicWorkRequestBuilder<NetworkCheckWorker>(15, TimeUnit.MINUTE)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "NetworkCheck",
                ExistingPeriodicWorkPolicy.KEEP,
                networkCheckRequest
            )
    }

}

