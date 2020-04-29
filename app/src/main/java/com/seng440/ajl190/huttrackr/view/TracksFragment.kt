package com.seng440.ajl190.huttrackr.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.TracksViewModel

class TracksFragment : Fragment() {

    companion object {
        fun newInstance() = TracksFragment()
    }

    private lateinit var viewModel: TracksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tracks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TracksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
