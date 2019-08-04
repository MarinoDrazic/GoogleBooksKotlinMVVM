package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zebra.isv.googlebookskotlinmvvm.R
import com.zebra.isv.googlebookskotlinmvvm.data.internal.ShowcaseItem
import kotlinx.android.synthetic.main.item_recyclerview.*

class MainShowCaseItem(val itemBooks: com.zebra.isv.googlebookskotlinmvvm.data.Response.Item) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            //add Experimental

            if (itemBooks.volumeInfo.imageLinks.thumbnail != null) {
                Glide.with(this.containerView)
                    .load(itemBooks.volumeInfo.imageLinks.thumbnail)
                    .into(imageView)
            }
            textViewTitle.text = itemBooks.volumeInfo.title
            textViewAuthor.text = itemBooks.saleInfo.saleability
            containerView.setOnClickListener {
                var bundle = bundleOf(
                    "book" to ShowcaseItem(
                        itemBooks.volumeInfo.title,
                        itemBooks.volumeInfo.authors.toString(),
                        itemBooks.volumeInfo.imageLinks.thumbnail,
                        itemBooks.volumeInfo.description.toString()
                    )
                )
                Navigation.findNavController(viewHolder.containerView).navigate(R.id.toBookShowCase, bundle)
            }
        }

    }


    override fun getLayout() = R.layout.item_recyclerview


}