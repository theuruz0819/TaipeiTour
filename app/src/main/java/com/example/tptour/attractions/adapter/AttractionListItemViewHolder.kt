package com.example.tptour.attractions.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.tptour.data.AttractionListItem
import com.example.tptour.databinding.ItemAttractionBinding

class AttractionListItemViewHolder(private val binding: ItemAttractionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setResult(attractionListItem: AttractionListItem) {
        binding.data = attractionListItem
        binding.executePendingBindings()
    }
}