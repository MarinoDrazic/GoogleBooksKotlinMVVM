package com.zebra.isv.googlebookskotlinmvvm

import android.app.Application
import com.zebra.isv.googlebookskotlinmvvm.data.GoogleBooksApiService
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepository
import com.zebra.isv.googlebookskotlinmvvm.data.reposotory.GoogleBooksRepositoryImp
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class GoogleBooksApplication:Application(),KodeinAware {
    override val kodein= Kodein.lazy {
        import(androidModule(this@GoogleBooksApplication))

        bind() from singleton { GoogleBooksApiService() }
        bind<GoogleBooksRepositoryImp>() with singleton { GoogleBooksRepositoryImp(instance()) }
        bind<GoogleBooksRepository>() with singleton { GoogleBooksRepositoryImp(instance())

        }


    }
}