package com.example.projectwizeline.app.presentation.view_helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectwizeline.R
import com.example.projectwizeline.databinding.ItemAskBidBinding
import com.example.projectwizeline.domain.constant.Constants
import com.example.projectwizeline.domain.entity.AskOrBid
import com.example.projectwizeline.util.Utils.toFormatCurrency

class DetailCryptoAdapter: ListAdapter<AskOrBid, DetailCryptoAdapter.ViewHolder>(DiffUtilCallbacks) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCryptoAdapter.ViewHolder {
        val binding = ItemAskBidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailCryptoAdapter.ViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    inner class ViewHolder(private val binding: ItemAskBidBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(book: AskOrBid) {
            if(book.type == Constants.ASK) {
                binding.root.setBackgroundColor(binding.root.context.resources.getColor(R.color.background_ask, null))
            } else {
                binding.root.setBackgroundColor(binding.root.context.resources.getColor(R.color.background_bid, null))
            }
            binding.itemAskBidTypeValue.text = book.type
            binding.itemAskBidNameValue.text = book.idBook
            binding.itemAskBidPriceValue.text = book.price?.toFormatCurrency()
            binding.itemAskBidAmountValue.text = book.amount.toString()
            if(layoutPosition == itemCount-1) {
                binding.line.visibility = View.GONE
            }
        }
    }
}

private object DiffUtilCallbacks: DiffUtil.ItemCallback<AskOrBid>() {
    override fun areItemsTheSame(oldItem: AskOrBid, newItem: AskOrBid): Boolean =
        oldItem.idBook == newItem.idBook

    override fun areContentsTheSame(oldItem: AskOrBid, newItem: AskOrBid): Boolean =
        oldItem == newItem
}