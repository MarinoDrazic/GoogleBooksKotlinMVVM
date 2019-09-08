package com.zebra.isv.googlebookskotlinmvvm.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leochuan.CarouselLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.zebra.isv.googlebookskotlinmvvm.MainShowCase.MainShowCaseItem
import com.zebra.isv.googlebookskotlinmvvm.R
import com.zebra.isv.googlebookskotlinmvvm.base.ScopedFragment
import com.zebra.isv.googlebookskotlinmvvm.data.Response.GoogleBooksResponse
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import com.leochuan.CenterSnapHelper
import com.leochuan.ScaleLayoutManager


class ProfileFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: ProfileViewModelFactory by instance()

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProfileViewModel::class.java)
        viewModel.fetchMatchData("Kanye")
        bindUI()
        lottieheart.setOnClickListener {
            lottieheart.apply {
                pauseAnimation()
                playAnimation()
            }
        }
        recyclerViewProfile.layoutManager =  ScaleLayoutManager.Builder(context,10).build()
        CenterSnapHelper().attachToRecyclerView(recyclerViewProfile)

    }

    private fun bindUI() = launch {

        viewModel.searchedData.observe(this@ProfileFragment, Observer {
            it?.let { listOfBooks ->
                val groupAdapter = GroupAdapter<ViewHolder>().apply {
                    addAll(listOfBooks.toMainShowCaseItems())
                }
                recyclerViewProfile.adapter = groupAdapter
                recyclerViewProfile.smoothScrollToPosition(1)

            }
        })
    }


    //TODO move this into a useCase
    private fun GoogleBooksResponse.toMainShowCaseItems(): List<MainShowCaseItem> {
        return this.items.map {
            MainShowCaseItem(it)
        }
    }
}
