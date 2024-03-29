package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        viewModel.fetchMatchData("Action")
        bindUI()
    }

    private fun bindUI() = launch {

        //Observe multiple live data objects??

        viewModel.action.observe(this@MainShowCaseFragment, Observer {
            it?.let { listOfBooks ->
                initRecycler(listOfBooks.toMainShowCaseItems(), "action")
            }
        })
        viewModel.advanture.observe(this@MainShowCaseFragment, Observer {
            it?.let { listOfBooks ->
                initRecycler(listOfBooks.toMainShowCaseItems(), "adventure")
            }
        })
        viewModel.drama.observe(this@MainShowCaseFragment, Observer {
            it?.let { listOfBooks ->
                initRecycler(listOfBooks.toMainShowCaseItems(), "drama")
            }
        })
        viewModel.mystery.observe(this@MainShowCaseFragment, Observer {
            it?.let { listOfBooks ->
                initRecycler(listOfBooks.toMainShowCaseItems(), "mystery")
            }
        })
    }

    private fun GoogleBooksResponse.toMainShowCaseItems(): List<MainShowCaseItem> {
        return this.items.map {
            MainShowCaseItem(it)
        }
    }

    private fun initRecycler(items: List<MainShowCaseItem>, selector: String) {

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        when (selector) {
            "adventure" -> {
                //For some reason setting group visibility doesn't work. Manual it is.
                progressBarAdvanture.visibility = View.GONE
                textViewLoadingAdventure.visibility = View.GONE
                recyclerViewAdvanture.apply {
                    adapter = groupAdapter
                }
            }
            "action" -> {
                progressBarAction.visibility = View.GONE
                textViewLoadingAction.visibility = View.GONE
                GroupLoadingAction.visibility = View.GONE
                recyclerViewAction.apply {
                    adapter = groupAdapter
                }
            }
            "drama" -> {
                progressBarDrama.visibility = View.GONE
                textViewLoadingDrama.visibility = View.GONE
                recyclerViewDrama.apply {
                    adapter = groupAdapter
                }
            }
            "mystery" -> {
                progressBarMystery.visibility = View.GONE
                textViewLoadingMystery.visibility = View.GONE
                recyclerViewMystery.apply {
                    adapter = groupAdapter
                }
            }
        }
    }

}
