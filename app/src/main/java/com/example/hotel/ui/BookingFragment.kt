package com.example.hotel.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.hotel.R
import com.example.hotel.databinding.FragmentBookingBinding


class BookingFragment : Fragment() {
    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var constraintLayoutTurist: ConstraintLayout
    private var isContainerExpanded = true
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        var currentState = 0
        val root: View = binding.root
        val emailEdtiText = binding.editTextTextEmailAddress
        val textView = binding.textView23
        val torist2 = binding.second
        val torist3 = binding.thri
        constraintLayoutTurist = root.findViewById(R.id.constraintLayoutTurist)
        imageView = root.findViewById(R.id.imageView3)
        binding.imageView5.setOnClickListener {
            when (currentState) {
                0-> {
                    torist2.visibility = View.VISIBLE
                    currentState = 1
                }
                1-> {
                    torist3.visibility = View.VISIBLE
                    currentState = 2
                }
               else -> {
                    Toast.makeText( requireContext(), "Максимальное количество туристов", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.imageView3.setOnClickListener {
            toggleContainerVisibility(constraintLayoutTurist, binding.imageView3)
        }
        binding.imageView2.setOnClickListener {
            findNavController().navigate(R.id.action_bookingFragment_to_hotelRoom)
        }
        binding.imageView65.setOnClickListener {
            toggleContainerVisibility(binding.constraintLayoutTurist2, binding.imageView65)
        }
        binding.imageView652.setOnClickListener {
            toggleContainerVisibility(binding.constraintLayoutTurist3, binding.imageView652)
        }
        val editTextListeners = mutableListOf<TextWatcher>()

        for (i in 100 until 117) { // Предполагается, что у вас есть 100 EditText
            val editText = binding.root.findViewById<EditText>(resources.getIdentifier("editText$i", "id", requireContext().packageName))
            val textView = binding.root.findViewById<TextView>(resources.getIdentifier("textView$i", "id", requireContext().packageName))

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.isNotEmpty() == true) {
                        textView.visibility = View.VISIBLE
                    } else {
                        textView.visibility = View.GONE
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            }

            editTextListeners.add(textWatcher)

            editText.addTextChangedListener(textWatcher)
        }

        fun areAllFieldsFilled(): Boolean {
            var atLeastOneVisible = false
            var allFieldsFilled = true

            for (i in 1..3) {
                val constraintLayout = when (i) {
                    1 -> binding.constraintLayoutTurist
                    2 -> binding.constraintLayoutTurist2
                    3 -> binding.constraintLayoutTurist3
                    else -> null
                }

                if (constraintLayout != null) {
                    val parentParentConstraintLayout = constraintLayout.parent as ConstraintLayout
                    if (parentParentConstraintLayout.visibility == View.VISIBLE) {
                        atLeastOneVisible = true

                        for (j in 0 until constraintLayout.childCount) {
                            val view = constraintLayout.getChildAt(j)
                            if (view is EditText) {
                                val text = view.text.toString()
                                if (text.isEmpty()) {
                                    allFieldsFilled = false
                                    view.setBackgroundResource(R.drawable.red_border)
                                } else {
                                    view.setBackgroundResource(R.drawable.shape3)
                                }
                            }
                        }
                    }
                }
            }
            return atLeastOneVisible && allFieldsFilled
        }

        binding.choiceRoom.setOnClickListener {
            if (areAllFieldsFilled()) {
                findNavController().navigate(R.id.action_bookingFragment_to_finishFragment)
            } else {
                Toast.makeText(requireContext(), "Заполните все обязательные поля", Toast.LENGTH_SHORT).show()
            }
        }

        emailEdtiText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString()
                if (text.any { it.isLetter() }) {
                    textView.visibility = View.VISIBLE
                } else {
                    textView.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        emailEdtiText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (!isValidEmail(text)) {
                    emailEdtiText.error = "Неверный формат почты"
                } else {
                    emailEdtiText.error = null
                }
            }
        })

        return root
    }
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    private fun toggleContainerVisibility(
        constraintLayout: ConstraintLayout,
        imageView: ImageView
    ) {
        if (isContainerExpanded) {
            constraintLayout.visibility = View.GONE
            isContainerExpanded = false
            imageView.setImageResource(R.drawable.round_keyboard_arrow_down_24)
        } else {
            constraintLayout.visibility = View.VISIBLE
            isContainerExpanded = true
            imageView.setImageResource(R.drawable.round_keyboard_arrow_up_24)
        }
    }


}