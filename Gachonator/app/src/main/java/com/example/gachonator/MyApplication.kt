package com.example.gachonator

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    lateinit var sRetrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        sRetrofit = provideRetrofit()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://your-base-url.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        @get:Synchronized
        lateinit var instance: MyApplication
            private set
    }
}