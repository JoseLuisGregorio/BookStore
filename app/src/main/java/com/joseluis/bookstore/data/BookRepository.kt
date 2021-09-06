package com.joseluis.bookstore.data

import com.joseluis.bookstore.data.model.BookModel
import com.joseluis.bookstore.data.model.BookProvider
import com.joseluis.bookstore.data.network.BookService
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: BookService,
    private val bookProvider: BookProvider
) {

    suspend fun getAllBooks(): ArrayList<BookModel> {
        val response = api.getAllBooks()
        bookProvider.books = response
        return response
    }

    suspend fun getBestSellersBooks(): ArrayList<String> {
        val response = api.getBestSellersBooks()
        bookProvider.bestSellersBooks = response
        return response
    }
}