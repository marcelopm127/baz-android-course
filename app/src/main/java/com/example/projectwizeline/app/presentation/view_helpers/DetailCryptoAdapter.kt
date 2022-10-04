package com.example.projectwizeline.app.presentation.view_helpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
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
            with(binding) {
                if(book.type == Constants.ASK) {
                    root.setBackgroundColor(root.context.resources.getColor(R.color.background_ask, null))
                } else {
                    root.setBackgroundColor(root.context.resources.getColor(R.color.background_bid, null))
                }
                itemAskBidTypeValue.text = book.type
                itemAskBidNameValue.text = book.idBook
                itemAskBidPriceValue.text = book.price?.toFormatCurrency()
                itemAskBidAmountValue.text = book.amount.toString()
                line.isVisible = layoutPosition != itemCount-1
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