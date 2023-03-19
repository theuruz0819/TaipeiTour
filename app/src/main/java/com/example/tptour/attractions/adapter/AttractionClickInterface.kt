package com.example.tptour.attractions.adapter

import com.example.tptour.data.AttractionListItem

interface AttractionClickInterface {
    fun onAttractionItemClick(attractionListItem: AttractionListItem)
}