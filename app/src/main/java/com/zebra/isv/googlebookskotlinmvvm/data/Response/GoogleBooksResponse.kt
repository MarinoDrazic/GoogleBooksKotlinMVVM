package com.zebra.isv.googlebookskotlinmvvm.data.Response


data class GoogleBooksResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)