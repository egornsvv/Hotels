package com.example.hotel.data.repository.repositoryimpl

import com.example.hotel.data.model.Room
import com.example.hotel.data.model.RoomsResponse
import com.example.hotel.data.remote.ApiService
import com.example.hotel.data.repository.DataRepositoryHotelHotelRoom
import retrofit2.Call

class DataRepositoryHotelHotelRoomImpl(private val apiService: ApiService) : DataRepositoryHotelHotelRoom {
    override fun getImagesWithTextHotel(): Call<RoomsResponse> {
        return apiService.getImagesWithTextHotel()
    }
}