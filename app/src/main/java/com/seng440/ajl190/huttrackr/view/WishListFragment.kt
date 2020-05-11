package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.WishListViewModel

class WishListFragment : Fragment() {

    companion object {
        fun newInstance() = WishListFragment()
    }

    private lateinit var viewModel: WishListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wish_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WishListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
