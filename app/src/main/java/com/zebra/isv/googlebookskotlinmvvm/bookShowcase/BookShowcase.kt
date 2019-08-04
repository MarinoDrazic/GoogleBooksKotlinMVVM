package com.zebra.isv.googlebookskotlinmvvm.bookShowcase

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.zebra.isv.googlebookskotlinmvvm.R
import com.zebra.isv.googlebookskotlinmvvm.data.internal.ShowcaseItem
import kotlinx.android.synthetic.main.book_showcase_fragment.*
import kotlinx.android.synthetic.main.item_recyclerview.*

class BookShowcase : Fragment() {

    companion object {
        fun newInstance() = BookShowcase()
    }

    private lateinit var viewModel: BookShowcaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_showcase_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BookShowcaseViewModel::class.java)
        // TODO: Use the ViewModel
        val showcaseItem = arguments?.getParcelable("book") as ShowcaseItem
        bindUI(showcaseItem)
    }

    fun bindUI(showcaseItem: ShowcaseItem) {
        Glide.with(this)
            .load(showcaseItem.image)
            .into(imageViewBookShowcase)
        textViewTitleShowcase.text = showcaseItem.Title
        if (showcaseItem.Author != null) {
            textViewAuthorShowcase.text = showcaseItem.Author
        } else
            textViewAuthorShowcase.text = "Unknown Author"
        if (showcaseItem.description != null) {
            textViewDescriptionShowcase.text = showcaseItem.description
        } else
            textViewDescriptionShowcase.text = "No description Provided"
    }

}
