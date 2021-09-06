package com.joseluis.bookstore.data.network

import com.joseluis.bookstore.core.RetrofitHelper
import com.joseluis.bookstore.data.model.BookModel
import com.joseluis.bookstore.data.model.ResponseApiBooks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookService @Inject constructor(private val api: BookApiClient) {

    suspend fun  getAllBooks() : ArrayList<BookModel> {
        return withContext(Dispatchers.IO){
            val response = api.getAllBooks()
            response.body()?.results?.books ?: ArrayList()
        }
    }

    suspend fun getBestSellersBooks() : ArrayList<String> {
        return withContext(Dispatchers.IO){
            val response = api.getBestSellersBooks()
            response.body()?.results?.best_sellers ?: ArrayList()
        }
    }
}