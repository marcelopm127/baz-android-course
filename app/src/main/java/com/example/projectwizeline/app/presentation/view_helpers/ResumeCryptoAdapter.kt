package com.example.projectwizeline.app.presentation.view_helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectwizeline.R
import com.example.projectwizeline.databinding.ItemCryptoBinding
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
            click.invoke(payload.idBook ?: "")
        }
    }

    inner class ViewHolder(private val binding: ItemCryptoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(payload: Payload) {

            val name = payload.idBook?.replace("_mxn", "")
            val imageResource = binding.root.context.resources.getIdentifier(name, "drawable", binding.root.context.packageName)

            val image = if(imageResource != 0) {
                imageResource
            } else {
                R.drawable.unknown
            }

            binding.imageViewIcon.setImageResource(image)
            binding.textViewName.text = name
            binding.textViewMaxPrice.text = payload.maximumPrice?.toFormatCurrency()
            binding.textViewMinPrice.text = payload.minimumPrice?.toFormatCurrency()
            if(layoutPosition == itemCount-1) {
                binding.line.visibility = View.GONE
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