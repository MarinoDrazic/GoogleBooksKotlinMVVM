package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository
import kotlinx.coroutines.launch

class MainShowCaseViewModel(
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModel() {

    private val _actionData by lazy { MutableLiveData<GoogleBooksResponse>() }
    val action: LiveData<GoogleBooksResponse> get() = _actionData

    private val _advanture by lazy { MutableLiveData<GoogleBooksResponse>() }
    val advanture: LiveData<GoogleBooksResponse> get() = _mystery

    private val _drama by lazy { MutableLiveData<GoogleBooksResponse>() }
    val drama: LiveData<GoogleBooksResponse> get() = _drama

    private val _mystery by lazy { MutableLiveData<GoogleBooksResponse>() }
    val mystery: LiveData<GoogleBooksResponse> get() = _mystery


    fun fetchMatchData(query: String) = launch {
        _actionData.postValue(googleBooksRepository.fetchBooks("action"))
        _mystery.postValue(googleBooksRepository.fetchBooks("mystery"))
        _advanture.postValue(googleBooksRepository.fetchBooks("adventure"))
        _drama.postValue(googleBooksRepository.fetchBooks("drama"))
    }



}
