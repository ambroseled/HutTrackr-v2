package com.seng440.ajl190.huttrackr.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.seng440.ajl190.huttrackr.R

class HutsFragment : Fragment() {

    companion object {
        fun newInstance() = HutsFragment()
    }

    private lateinit var viewModel: HutsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.huts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HutsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
