package com.example.tripmate.dataClasses

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items") val items: List<SearchItem>?
)
