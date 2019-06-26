package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository

class MainShowCaseViewModelFactory(
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainShowCaseViewModel(googleBooksRepository) as T
    }
}