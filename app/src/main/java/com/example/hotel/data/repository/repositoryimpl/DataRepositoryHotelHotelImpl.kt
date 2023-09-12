package com.example.hotel.data.repository.repositoryimpl

import com.example.hotel.data.model.DataResponse
import com.example.hotel.data.remote.ApiService
import com.example.hotel.data.repository.DataRepositoryHotel
import retrofit2.Call

class DataRepositoryHotelHotelImpl(private val apiService: ApiService) : DataRepositoryHotel {
    override fun getImagesWithText(): Call<DataResponse> {
        return apiService.getImagesWithText()
    }
}