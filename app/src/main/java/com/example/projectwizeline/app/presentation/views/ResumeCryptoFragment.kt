package com.example.projectwizeline.app.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.projectwizeline.R
import com.example.projectwizeline.app.presentation.view_helpers.ResumeCryptoAdapter
import com.example.projectwizeline.app.presentation.view_models.ResumeCryptoViewModel
import com.example.projectwizeline.databinding.FragmentResumeCryptoBinding

class ResumeCryptoFragment: Fragment() {

    private var _binding: FragmentResumeCryptoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResumeCryptoViewModel by activityViewModels()
    private lateinit var adapter: ResumeCryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ResumeCryptoAdapter { idBook ->
            val action = ResumeCryptoFragmentDirections.actionResumeCryptoFragmentToDetailCryptoFragment(idBook)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResumeCryptoBinding.inflate(inflater, container, false)

        binding.fragmentResumeCryptoRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getResumeCryptos()

        viewModel.resumeCryptos.observe(requireActivity()) { resume ->
            if(resume.payload?.isNotEmpty() == true) {
                adapter.submitList(resume.payload)
            } else {
                Toast.makeText(requireActivity(), resources.getText(R.string.empty_data), Toast.LENGTH_SHORT).show()
            }
        }
    }

}