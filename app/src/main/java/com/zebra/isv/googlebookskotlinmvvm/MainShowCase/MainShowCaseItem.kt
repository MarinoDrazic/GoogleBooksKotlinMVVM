package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zebra.isv.googlebookskotlinmvvm.R
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse

class MainShowCaseItem(val googleBooksResponse: GoogleBooksResponse): Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {

        }
    }

    override fun getLayout() = R.layout.item_recyclerview

}