package com.example.hotel.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hotel.R
import com.example.hotel.app.MyApplication
import com.example.hotel.data.model.Room
import com.example.hotel.data.model.RoomsResponse
import com.example.hotel.data.repository.DataRepositoryHotel
import com.example.hotel.data.repository.DataRepositoryHotelHotelRoom
import com.example.hotel.databinding.FragmentHotelRoomBinding
import com.example.hotel.ui.adapter.ImageAdapter
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class HotelRoom : Fragment() {
    @Inject
    lateinit var DataRepositoryHotelHotelRoom: DataRepositoryHotelHotelRoom
    private var _binding: FragmentHotelRoomBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelRoomBinding.inflate(inflater,  container, false)
        val root: View = binding.root
        (activity?.application as MyApplication).appComponent.inject(this)
        val viewPager: ViewPager2 = binding.viewPager
        val viewPager2: ViewPager2 = binding.viewPager2
        binding.imageView2.setOnClickListener {
            findNavController().navigate(R.id.action_hotelRoom_to_hotelFragment)
        }
        binding.choiceRoom.setOnClickListener {
            findNavController().navigate(R.id.action_hotelRoom_to_bookingFragment)
        }
        binding.choiceRoom12.setOnClickListener {
            findNavController().navigate(R.id.action_hotelRoom_to_bookingFragment)
        }
        val getImagesWithTextHotel = DataRepositoryHotelHotelRoom.getImagesWithTextHotel()
        getImagesWithTextHotel.enqueue(object : retrofit2.Callback<RoomsResponse> {
            override fun onResponse(call: Call<RoomsResponse>, response: Response<RoomsResponse>) {
                if (response.isSuccessful) {
                    val roomsResponse = response.body()
                    if (roomsResponse!= null) {
                        val rooms = roomsResponse.rooms

                        val firstRoom = rooms[0]
                        viewPager.adapter = ImageAdapter(firstRoom.image_urls)


                        val secondRoom = rooms[1]
                        viewPager2.adapter = ImageAdapter(secondRoom.image_urls)
                    }
                }

            }

            override fun onFailure(call: Call<RoomsResponse>, t: Throwable) {
                Log.e( TAG, "onFailure: ${t.message}")
            }

        })
            return root

}
}