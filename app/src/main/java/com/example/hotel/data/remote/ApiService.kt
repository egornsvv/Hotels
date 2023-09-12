package com.example.hotel.data.remote

import com.example.hotel.data.model.DataResponse
import com.example.hotel.data.model.Room
import com.example.hotel.data.model.RoomsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    fun getImagesWithText(): Call<DataResponse>

    @GET("v3/f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    fun getImagesWithTextHotel(): Call<RoomsResponse>
}