package com.example.hotel.di

import com.example.hotel.data.remote.ApiService
import com.example.hotel.data.repository.DataRepositoryHotel
import com.example.hotel.data.repository.DataRepositoryHotelHotelRoom
import com.example.hotel.data.repository.repositoryimpl.DataRepositoryHotelHotelImpl
import com.example.hotel.data.repository.repositoryimpl.DataRepositoryHotelHotelRoomImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    fun provideDataRepositoryHotel(apiService: ApiService): DataRepositoryHotel {
        return DataRepositoryHotelHotelImpl(apiService)
    }
    @Provides
    fun provideDataRepositoryHotelRoom(apiService: ApiService): DataRepositoryHotelHotelRoom {
        return DataRepositoryHotelHotelRoomImpl(apiService)
    }
}