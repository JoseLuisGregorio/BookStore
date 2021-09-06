package com.joseluis.bookstore.data.model

import com.google.gson.annotations.SerializedName

data class ResponseApiBooks(
    @SerializedName("results") val results : Results
)

data class Results(
    @SerializedName("books") val books : ArrayList<BookModel>
)

data class BookModel(
    @SerializedName("isbn") val isbn : String,
    @SerializedName("title") val title : String,
    @SerializedName("author") val author : String,
    @SerializedName("description") val description : String,
    @SerializedName("genre") val genre : String,
    @SerializedName("img") val img : String,
    @SerializedName("imported") val imported : Boolean
)