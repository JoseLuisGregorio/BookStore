package com.joseluis.bookstore.data.network

import com.joseluis.bookstore.data.model.ResponseApiBestSellersBooks
import com.joseluis.bookstore.data.model.ResponseApiBooks
import retrofit2.Response
import retrofit2.http.GET

interface BookApiClient {
    @GET ("/ejgteja/files/main/books.json")
    suspend fun getAllBooks() : Response<ResponseApiBooks>

    @GET ("/ejgteja/files/main/best_sellers.json")
    suspend fun getBestSellersBooks() : Response<ResponseApiBestSellersBooks>
}