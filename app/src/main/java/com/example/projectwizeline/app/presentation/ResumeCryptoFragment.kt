package com.example.projectwizeline.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projectwizeline.R
import com.example.projectwizeline.databinding.FragmentResumeCryptoBinding

class ResumeCryptoFragment: Fragment() {

    private var _binding: FragmentResumeCryptoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentResumeCryptoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener{
            findNavController().navigate(R.id.action_resumeCryptoFragment_to_detailCryptoFragment)
        }
    }

}