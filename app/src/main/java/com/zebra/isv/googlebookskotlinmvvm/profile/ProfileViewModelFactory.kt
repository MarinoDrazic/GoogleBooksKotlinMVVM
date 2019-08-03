package com.zebra.isv.googlebookskotlinmvvm.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository

class ProfileViewModelFactory (
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(googleBooksRepository) as T
    }
}