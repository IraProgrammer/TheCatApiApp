package com.example.myapplication.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CatNw(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("url")
    @Expose
    val url: String
)