package com.example.tripmate

import com.google.gson.annotations.SerializedName

data class SearchItem(
    @SerializedName("link") val link: String?
)
