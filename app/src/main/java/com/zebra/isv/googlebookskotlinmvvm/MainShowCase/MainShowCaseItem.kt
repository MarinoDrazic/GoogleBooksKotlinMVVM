package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zebra.isv.googlebookskotlinmvvm.R
import kotlinx.android.synthetic.main.item_recyclerview.*

class MainShowCaseItem(val itemBooks :com.zebra.isv.googlebookskotlinmvvm.data.Response.Item):Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            //add Experimental

            if (itemBooks.volumeInfo.imageLinks.thumbnail!=null) {
                Glide.with(this.containerView)
                    .load(itemBooks.volumeInfo.imageLinks.thumbnail)
                    .into(imageView)
            }
            textViewTitle.text = itemBooks.volumeInfo.title
            textViewAuthor.text = itemBooks.saleInfo.saleability
        }
    }



    override fun getLayout() = R.layout.item_recyclerview


}