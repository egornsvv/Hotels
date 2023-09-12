package com.example.hotel.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hotel.R
import com.example.hotel.databinding.FragmentFinishBinding


class FinishFragment : Fragment() {

    var _binding: FragmentFinishBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)


        binding.imageView2.setOnClickListener {
            findNavController().navigate(R.id.action_finishFragment_to_bookingFragment)
        }
        binding.choiceRoom.setOnClickListener {
            findNavController().navigate(R.id.action_finishFragment_to_hotelFragment)
        }








        return binding.root



    }


}