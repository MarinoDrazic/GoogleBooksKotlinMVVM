package com.zebra.isv.googlebookskotlinmvvm.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    private val _searchedData by lazy { MutableLiveData<GoogleBooksResponse>() }
    val searchedData: LiveData<GoogleBooksResponse> get() = _searchedData

    fun fetchMatchData(query: String) = launch {
        _searchedData.postValue(googleBooksRepository.fetchBooks(query))
    }
}
