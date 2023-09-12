package com.example.hotel.di

import com.example.hotel.ui.HotelFragment
import com.example.hotel.ui.HotelRoom
import com.example.hotel.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(fragment: HotelFragment)
    fun inject(activity: MainActivity)
    fun inject(fragment: HotelRoom)
}