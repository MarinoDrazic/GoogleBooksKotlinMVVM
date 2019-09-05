package com.zebra.isv.googlebookskotlinmvvm.bookShowcase

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.zebra.isv.googlebookskotlinmvvm.data.internal.ShowcaseItem
import kotlinx.android.synthetic.main.book_showcase_fragment.*
import kotlinx.coroutines.launch


class BookShowcase : Fragment() {

    companion object {
        fun newInstance() = BookShowcase()
    }

    private lateinit var viewModel: BookShowcaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.zebra.isv.googlebookskotlinmvvm.R.layout.book_showcase_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BookShowcaseViewModel::class.java)
        // TODO: Use the ViewModel
        val showcaseItem = arguments?.getParcelable("book") as ShowcaseItem
        bindUI(showcaseItem)
        lottiecheck.setOnClickListener {
            lottiecheck.apply {
                pauseAnimation()
                playAnimation()
            }
        }
        lottiedownload.setOnClickListener {
            lottiedownload.apply {
                pauseAnimation()
                playAnimation()
            }
            launchBrowser(showcaseItem)
        }
    }
    private fun launchBrowser(showcaseItem: ShowcaseItem) = launch{
        Thread.sleep(4000)
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(showcaseItem.webReaderLink))
        startActivity(browserIntent)
    }
    private fun bindUI(showcaseItem: ShowcaseItem) {
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
        if(showcaseItem.webReaderLink == null){
            lottiedownload.visibility=View.GONE
        }
    }

}
