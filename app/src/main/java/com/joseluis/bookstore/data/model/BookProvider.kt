package com.joseluis.bookstore.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookProvider @Inject constructor() {
    var books :ArrayList<BookModel> = ArrayList()
    var bestSellersBooks :ArrayList<String> = ArrayList()
}