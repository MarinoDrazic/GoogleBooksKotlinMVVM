package com.zebra.isv.googlebookskotlinmvvm.search

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
            lottieAnimationView.pauseAnimation()
            lottieAnimationView.visibility = View.GONE
            textViewEmptySearch.visibility = View.GONE
            lottieSearchDone.visibility = View.VISIBLE
            lottieSearchDone.playAnimation()
            Handler().postDelayed({
                bindBooks(it)
            }, 1500)

        })
        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId and EditorInfo.IME_MASK_ACTION != 0) {
                viewModel.fetchMatchData(editText.text.toString())
                editText.clearAnimation()
                editText.hideKeyboard()
                true
            } else {
                false
            }
        }
    }
    fun bindBooks(it:GoogleBooksResponse)= launch {
        it?.let { listOfBooks ->
            val groupAdapter = GroupAdapter<ViewHolder>().apply {
                addAll(listOfBooks.toSearchCaseItem())
            }
            recyclerViewSearch.adapter = groupAdapter
        }
        lottieSearchDone.pauseAnimation()
        lottieSearchDone.visibility = View.GONE
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    //TODO move this into a useCase
    private fun GoogleBooksResponse.toSearchCaseItem(): List<SearchCaseItem> {
        return this.items.map {
            SearchCaseItem(it)
        }
    }

}
