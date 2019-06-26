package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zebra.isv.googlebookskotlinmvvm.R
import com.zebra.isv.googlebookskotlinmvvm.base.ScopedFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.Internal.instance
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class MainShowCaseFragment : ScopedFragment(),KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory:MainShowCaseViewModelFactory by instance()


    private lateinit var viewModel: MainShowCaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_show_case_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(MainShowCaseViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = launch{
        val currentBooks = viewModel.Books.await()
        currentBooks

    }

}
