package com.zebra.isv.googlebookskotlinmvvm.search

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zebra.isv.googlebookskotlinmvvm.R
import kotlinx.android.synthetic.main.search_item.*
import java.lang.Exception

class SearchCaseItem(val itemBooks: com.zebra.isv.googlebookskotlinmvvm.data.Response.Item) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            //add Experimental

            if (itemBooks.volumeInfo.imageLinks.thumbnail != null) {
                Glide.with(this.containerView)
                    .load(itemBooks.volumeInfo.imageLinks.thumbnail)
                    .into(imageViewBookProfile)
            }
            textViewTitleSearch.text = itemBooks.volumeInfo.title
            try {
                textViewAuthorSearch.text = itemBooks.volumeInfo.authors.toString()
            }catch (f:Exception){}

        }
    }
    override fun getLayout() = R.layout.search_item
}