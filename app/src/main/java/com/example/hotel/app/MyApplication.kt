package com.example.hotel.app

import android.app.Application
import com.example.hotel.di.AppComponent
import com.example.hotel.di.DaggerAppComponent
import com.example.hotel.di.DataModule

class MyApplication : Application() {

    lateinit var  appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .dataModule(DataModule())
            .build()
    }
}