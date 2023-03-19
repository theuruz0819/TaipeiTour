package com.example.tptour.data

import com.google.gson.annotations.SerializedName

data class AttractionListResponse(
    @SerializedName("total") var total: Int? = null,
    @SerializedName("data") var data: List<AttractionListItem> = listOf()
)

data class AttractionListItem(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("name_zh") var nameZh: String? = null,
    @SerializedName("open_status") var openStatus: Int? = null,
    @SerializedName("introduction") var introduction: String? = null,
    @SerializedName("open_time") var open_time: String? = null,
    @SerializedName("zipcode") var zipcode: String? = null,
    @SerializedName("distric") var distric: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("tel") var tel: String? = null,
    @SerializedName("fax") var fax: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("months") var months: String? = null,
    @SerializedName("nlat") var nlat: Double? = null,
    @SerializedName("elong") var elong: Double? = null,
    @SerializedName("official_site") var official_site: String? = null,
    @SerializedName("facebook") var facebook: String? = null,
    @SerializedName("ticket") var ticket: String? = null,
    @SerializedName("remind") var remind: String? = null,
    @SerializedName("staytime") var staytime: String? = null,
    @SerializedName("modified") var modified: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("category") var category: List<Category> = listOf(),
    @SerializedName("target") var target: List<Target> = listOf(),
    @SerializedName("service") var service: List<Service> = listOf(),
    @SerializedName("images") var images: List<Image> = listOf()
){
    fun getCoverImageUrl():String?{
        return images.getOrNull(0)?.src
    }
}

data class Category(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null

)

data class Target(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)

data class Service(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)
data class Image(
    @SerializedName("src") var src: String? = null,
    @SerializedName("subject") var subject: String? = null,
    @SerializedName("ext") var ext: String? = null
)
