package com.example.projectwizeline.app.presentation.view_helpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectwizeline.R
import com.example.projectwizeline.databinding.ItemCryptoBinding
import com.example.projectwizeline.domain.constant.Constants
import com.example.projectwizeline.domain.entity.Payload
import com.example.projectwizeline.util.Utils.toFormatCurrency

class ResumeCryptoAdapter(private val click: (String) -> Unit): ListAdapter<Payload, ResumeCryptoAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val payload = getItem(position)
        holder.bind(payload)
        holder.itemView.setOnClickListener {
            click.invoke(payload.idBook.orEmpty())
        }
    }

    inner class ViewHolder(private val binding: ItemCryptoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(payload: Payload) {
            with(binding) {
                val name = payload.idBook?.replace(Constants.MXN_COMPARE, Constants.SPACE)
                val imageResource = root.context.resources.getIdentifier(name, Constants.DRAWABLE_TYPE, binding.root.context.packageName)

                val image = if(imageResource != 0) {
                    imageResource
                } else {
                    R.drawable.unknown
                }

                imageViewIcon.setImageResource(image)
                textViewName.text = name
                textViewMaxPrice.text = payload.maximumPrice?.toFormatCurrency()
                textViewMinPrice.text = payload.minimumPrice?.toFormatCurrency()
                line.isVisible = layoutPosition != itemCount-1
            }
        }
    }
}

private object DiffUtilCallback: DiffUtil.ItemCallback<Payload>() {
    override fun areItemsTheSame(oldItem: Payload, newItem: Payload): Boolean =
        oldItem.idBook == newItem.idBook

    override fun areContentsTheSame(oldItem: Payload, newItem: Payload): Boolean =
        oldItem == newItem

}