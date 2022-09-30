package com.example.projectwizeline.app.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.projectwizeline.app.presentation.view_helpers.DetailCryptoAdapter
import com.example.projectwizeline.app.presentation.view_models.DetailCryptoViewModel
import com.example.projectwizeline.databinding.FragmentDetailCryptoBinding
import com.example.projectwizeline.util.Utils.toFormatCurrency

class DetailCryptoFragment: Fragment() {

    private var _binding: FragmentDetailCryptoBinding? = null
    private val binding get() = _binding!!

    private val args: DetailCryptoFragmentArgs by navArgs()

    private val viewModel: DetailCryptoViewModel by activityViewModels()
    private lateinit var adapter: DetailCryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = DetailCryptoAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailCryptoBinding.inflate(inflater, container, false)

        binding.fragmentDetailCryptoRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetailTicket(args.idBook.toString())

        viewModel.detail.observe(requireActivity()) { ticker ->
            binding.fragmentDetailCryptoLastPrice.text = ticker.payloadDetail?.last?.toFormatCurrency()
            binding.fragmentDetailCryptoTextViewHighPriceValue.text = ticker.payloadDetail?.high?.toFormatCurrency()
            binding.fragmentDetailCryptoTextViewLowPriceValue.text = ticker.payloadDetail?.low?.toFormatCurrency()
        }

        viewModel.orderBook.observe(requireActivity()) { payloadOrder ->
            adapter.submitList(payloadOrder.asksAndBids)
        }
    }
}