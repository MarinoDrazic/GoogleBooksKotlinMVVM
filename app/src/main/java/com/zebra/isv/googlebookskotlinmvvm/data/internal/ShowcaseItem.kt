package com.zebra.isv.googlebookskotlinmvvm.data.internal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ShowcaseItem(val Title: String, val Author: String,val image :String,val description: String,val webReaderLink : String?) : Parcelable {
}