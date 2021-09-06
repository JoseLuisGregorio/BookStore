package com.joseluis.bookstore.data.model

import com.google.gson.annotations.SerializedName

data class ResponseApiBestSellersBooks(
    @SerializedName("results") val results : BestSellers
)

data class BestSellers(
    @SerializedName("best_sellers") val best_sellers : ArrayList<String>
)