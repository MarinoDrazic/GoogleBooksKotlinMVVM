package com.zebra.isv.googlebookskotlinmvvm.MainShowCase

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zebra.isv.googlebookskotlinmvvm.R


class MainShowCaseFragment : Fragment() {

    companion object {
        fun newInstance() = MainShowCaseFragment()
    }

    private lateinit var viewModel: MainShowCaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_show_case_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainShowCaseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
