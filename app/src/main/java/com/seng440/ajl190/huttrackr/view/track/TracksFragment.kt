package com.seng440.ajl190.huttrackr.view.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.view.adpater.TrackTabAdapter
import com.seng440.ajl190.huttrackr.viewmodel.TracksViewModel

class TracksFragment : Fragment() {

    private lateinit var viewModel: TracksViewModel
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tracks_fragment, container, false)
        tabLayout = view.findViewById(R.id.tracksTabLayout)
        viewPager = view.findViewById(R.id.tracksTabPager)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TracksViewModel::class.java)

        val tabAdapter = TrackTabAdapter(
            requireContext(),
            this.parentFragmentManager
        )
        viewPager.adapter = tabAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

}
