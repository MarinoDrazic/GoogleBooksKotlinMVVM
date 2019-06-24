package com.zebra.isv.googlebookskotlinmvvm.data.reposotory

interface GoogleBooksRepository {
     suspend fun fetBooks(query:String)
}