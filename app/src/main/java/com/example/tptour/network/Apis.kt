package com.example.tptour.network

import com.example.tptour.data.AttractionListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Apis {
    @Headers("accept: application/json")
    @GET(ApiPath.ATTRACTIONS)
    fun getAttractions(@Path("lang") lang: String, @Query("page") page:Int): Single<AttractionListResponse>
}