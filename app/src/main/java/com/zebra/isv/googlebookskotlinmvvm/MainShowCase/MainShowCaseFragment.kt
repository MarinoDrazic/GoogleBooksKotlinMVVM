package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.zebra.isv.googlebookskotlinmvvm.R
import com.zebra.isv.googlebookskotlinmvvm.base.ScopedFragment
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import kotlinx.android.synthetic.main.main_show_case_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class MainShowCaseFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: MainShowCaseViewModelFactory by instance()


    private lateinit var viewModel: MainShowCaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_show_case_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainShowCaseViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = launch {
        //bilo bi super kad bi ovo bilu u Viewmodelu da se ne requerry svaki put kad okrenes ekran...
        //TODO maybe move this call to viewmodel.

        val currentBooks = viewModel.books.await()
        currentBooks.observe(this@MainShowCaseFragment, Observer {
            it?.let { listOfBooks ->
                GroupLoading.visibility = View.GONE
                initRecyclerView(listOfBooks.toMainShowCaseItems())
            }
        })

    }

    private fun GoogleBooksResponse.toMainShowCaseItems(): List<MainShowCaseItem> {
        return this.items.map {
            MainShowCaseItem(it)
        }
    }

    private fun initRecyclerView(items: List<MainShowCaseItem>) {

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        recyclerView.apply {
            layoutManager= LinearLayoutManager(this@MainShowCaseFragment.context)
            adapter=groupAdapter
        }
    }

}
