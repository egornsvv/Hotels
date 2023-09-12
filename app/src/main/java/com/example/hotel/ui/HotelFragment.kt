package com.example.hotel.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hotel.R
import com.example.hotel.app.MyApplication
import com.example.hotel.data.model.DataResponse
import com.example.hotel.data.repository.DataRepositoryHotel
import com.example.hotel.databinding.FragmentHotelBinding
import com.example.hotel.ui.adapter.ImageAdapter
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class HotelFragment : Fragment() {
    @Inject
    lateinit var DataRepositoryHotel: DataRepositoryHotel
    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (activity?.application as MyApplication).appComponent.inject(this)

        val viewPager2: ViewPager2 = binding.viewPager
        binding.choiceRoom.setOnClickListener {
            findNavController().navigate(R.id.action_hotelFragment_to_hotelRoom)
        }

        val imagesWithText = DataRepositoryHotel.getImagesWithText()
        imagesWithText.enqueue(object : retrofit2.Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    val dataResponse: DataResponse? = response.body()
                    val imageUrls: List<String> = dataResponse?.image_urls ?: emptyList()
                    dataResponse?.let {
                        binding.forTurTextView.text = it.price_for_it
                        binding.hoteDiscriptionTextView.text = it.about_the_hotel.description
                    }
                    val imageAdapter = ImageAdapter(imageUrls)
                    viewPager2.adapter = imageAdapter
                }
                else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return root
    }

}