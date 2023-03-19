package com.example.tptour.attractions.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.tptour.data.AttractionListItem
import com.example.tptour.databinding.ItemAttractionBinding
import java.util.*


class AttractionPagingAdapter : PagingDataAdapter<AttractionListItem, AttractionListItemViewHolder>(
    INDIFFERENCE
) {
    var attractionClickInterface: AttractionClickInterface? = null
    companion object {
        val INDIFFERENCE = object : DiffUtil.ItemCallback<AttractionListItem>() {
            override fun areItemsTheSame(oldItem: AttractionListItem, newItem: AttractionListItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AttractionListItem, newItem: AttractionListItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAttractionBinding.inflate(layoutInflater, parent, false)
        return AttractionListItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AttractionListItemViewHolder, position: Int) {
        val info = getItem(position)
        if (info != null) {
            holder.setResult(info)
        }
        holder.itemView.setOnClickListener {
            getItem(position)?.let {
                attractionClickInterface?.onAttractionItemClick(it)
            }
        }
    }
}