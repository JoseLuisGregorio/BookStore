package com.joseluis.bookstore.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.joseluis.bookstore.R
import com.joseluis.bookstore.data.model.BookModel

class BooksAdapter(var context: Context, var books: ArrayList<BookModel> ): RecyclerView.Adapter<BooksAdapter.BooksHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
        return BooksHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false),context)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        holder.bind(books[position])
    }

    class BooksHolder(view: View, val context: Context): RecyclerView.ViewHolder(view){
        private val bookIllustration: ShapeableImageView =itemView.findViewById(R.id.imageView_bookIllustration)
        private val title: MaterialTextView = itemView.findViewById(R.id.textView_title)
        private val imported: MaterialTextView = itemView.findViewById(R.id.textView_imported)
        private val author: MaterialTextView = itemView.findViewById(R.id.textView_author)
        private val isbn: MaterialTextView = itemView.findViewById(R.id.textView_isbn)
        private val description: MaterialTextView = itemView.findViewById(R.id.textView_description)

        fun bind(data:BookModel){
            title.text = data.title
            author.text = data.author
            isbn.text =  String.format(context.getString(R.string.isbn),data.isbn)
            description.text = data.description
            imported.isVisible = data.imported
            Glide.with(context)
                .load(data.img)
                .into(bookIllustration)
        }
    }
}