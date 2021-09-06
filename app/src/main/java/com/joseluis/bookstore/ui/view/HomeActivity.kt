package com.joseluis.bookstore.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.joseluis.bookstore.R
import com.joseluis.bookstore.data.model.BookModel
import com.joseluis.bookstore.databinding.ActivityHomeBinding
import com.joseluis.bookstore.ui.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val bookViewModel: BookViewModel by viewModels()

    var adapter: BooksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initNavigationListener()
        bookViewModel.onCreate()
        bookViewModel.getBestSellersBooks()

        bookViewModel.bookModel.observe(this, {
            adapter = BooksAdapter(this, it)
            binding.recyclerViewBooks.adapter = adapter
        })

        bookViewModel.isLoading.observe(this, { isVisible ->
            binding.progressCircular.isVisible = isVisible
        })
    }

    private fun initRecyclerView() {
        binding.recyclerViewBooks.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initNavigationListener() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_navigation -> {
                    bookViewModel.bookModel.observe(this, { books ->
                        refreshInformation(books)
                    })
                }
                R.id.nav_best_sellers -> {
                    bookViewModel.bestSellersBookModel.observe(this, { bestSellers ->
                        bestSellers(bestSellers)
                    })
                }
                R.id.nav_history -> {
                    applyFilter("History")
                }
                R.id.nav_science -> {
                    applyFilter("Science")
                }
                R.id.nav_business -> {
                    applyFilter("Business")
                }
            }
            true
        }
    }

    private fun applyFilter(filter: String) {
        bookViewModel.bookModel.observe(this, { books ->
            val filter = books.filter { it.genre == filter }
            refreshInformation(ArrayList(filter))
        })
    }

    private fun refreshInformation(books: ArrayList<BookModel>) {
        adapter?.books = books
        adapter?.notifyDataSetChanged()
    }

    private fun bestSellers(bestSellers: ArrayList<String>) {
        var bestSellersArray: ArrayList<BookModel> = ArrayList()
        bookViewModel.bookModel.observe(this, { books ->
            books.forEach { book ->
                bestSellers.forEach { item ->
                    if (book.isbn == item) {
                        bestSellersArray.add(book)
                    }
                }
            }

            refreshInformation(bestSellersArray)
        })

    }
}
