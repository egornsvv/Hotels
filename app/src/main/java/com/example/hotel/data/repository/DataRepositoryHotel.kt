package com.example.hotel.data.repository

import com.example.hotel.data.model.DataResponse
import retrofit2.Call

interface DataRepositoryHotel {
    fun getImagesWithText(): Call<DataResponse>
}