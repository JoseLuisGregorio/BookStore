package com.joseluis.bookstore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseluis.bookstore.data.model.BookModel
import com.joseluis.bookstore.data.model.BookProvider
import com.joseluis.bookstore.domain.GetBestSellersBooksUseCase
import com.joseluis.bookstore.domain.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val getBestSellersBooksUseCase: GetBestSellersBooksUseCase,
    private val bookProvider: BookProvider,
) : ViewModel() {

    val bookModel = MutableLiveData<ArrayList<BookModel>>()
    val bestSellersBookModel = MutableLiveData<ArrayList<String>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getBooksUseCase()
            if (!result.isNullOrEmpty()) {
                bookModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getBestSellersBooks() {
        viewModelScope.launch {
            val result = getBestSellersBooksUseCase()
            if (!result.isNullOrEmpty()) {
                bestSellersBookModel.postValue(result)
            }
        }
    }

    fun getArrayBooks() {
        val books = bookProvider.books
        bookModel.postValue(ArrayList(books))
    }
}