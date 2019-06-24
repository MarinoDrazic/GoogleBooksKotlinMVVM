package com.zebra.isv.googlebookskotlinmvvm.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApiService {

    @GET("books/v1/volumes")
    fun getBooks(
        @Query("q") query:String
    ): Deferred<GoogleBooksResponse>

    companion object{
        operator fun invoke():GoogleBooksApiService{
        val okHttpClient = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.googleapis.com/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleBooksApiService::class.java)
        }
    }
}