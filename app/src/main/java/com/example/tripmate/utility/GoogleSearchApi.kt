package com.example.tripmate.utility

import com.example.tripmate.dataClasses.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSearchApi {
    @GET("customsearch/v1")
    fun getImages(
        @Query("q") query: String,
        @Query("cx") cx: String,
        @Query("key") apiKey: String,
        @Query("searchType") searchType: String = "image"
    ): Call<SearchResponse>
}