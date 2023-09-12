package com.example.hotel.data.repository

import retrofit2.Call
import com.example.hotel.data.model.Room
import com.example.hotel.data.model.RoomsResponse

interface DataRepositoryHotelHotelRoom {
    fun getImagesWithTextHotel(): Call<RoomsResponse>
}