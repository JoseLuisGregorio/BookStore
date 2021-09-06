package com.joseluis.bookstore.domain

import com.joseluis.bookstore.data.BookRepository
import com.joseluis.bookstore.data.model.BookModel
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(): ArrayList<BookModel> = repository.getAllBooks()
}