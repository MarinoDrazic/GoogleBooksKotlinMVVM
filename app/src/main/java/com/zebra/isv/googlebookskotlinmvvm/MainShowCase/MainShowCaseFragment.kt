package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.zebra.isv.googlebookskotlinmvvm.R
import com.zebra.isv.googlebookskotlinmvvm.base.ScopedFragment
import kotlinx.android.synthetic.main.main_show_case_fragment.*
import kotlinx.coroutines.launch
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
        //bilo bi super kad bi ovo bilu u Viewmodelu da se ne requerry svaki put kad okrenes ekran...
        //TODO maybe move this call to viewmodel.

        val currentBooks = viewModel.books.await()
        currentBooks.observe(this@MainShowCaseFragment, Observer {
            GroupLoading.visibility = View.GONE
        })

    }

}
