package com.zebra.isv.googlebookskotlinmvvm.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.zebra.isv.googlebookskotlinmvvm.base.ScopedFragment
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class SearchFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: SearchViewModelFactory by instance()

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.zebra.isv.googlebookskotlinmvvm.R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchViewModel::class.java)
        bindUI()
        lottieAnimationView.playAnimation()
    }

    private fun bindUI() = launch {

        viewModel.searchedData.observe(this@SearchFragment, Observer {
            it?.let { listOfBooks ->
                val groupAdapter = GroupAdapter<ViewHolder>().apply {
                    addAll(listOfBooks.toSearchCaseItem())
                }
                recyclerViewSearch.adapter = groupAdapter
                lottieAnimationView.pauseAnimation()
                lottieAnimationView.visibility = View.GONE
                textViewEmptySearch.visibility = View.GONE
            }
        })
        editText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(view: View, keyCode: Int, keyevent: KeyEvent): Boolean {
                return if (keyevent.getAction() === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewModel.fetchMatchData(editText.text.toString())
                    editText.clearAnimation()
                    true
                } else false
            }
        })
    }


    //TODO move this into a useCase
    private fun GoogleBooksResponse.toSearchCaseItem(): List<SearchCaseItem> {
        return this.items.map {
            SearchCaseItem(it)
        }
    }

}
