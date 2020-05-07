package com.seng440.ajl190.huttrackr.view.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.view.adpater.TracksRecyclerAdapter
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.view.decorator.GridSpacingItemDecoration
import com.seng440.ajl190.huttrackr.viewmodel.TracksListViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.TracksListViewModelFactory
import kotlinx.android.synthetic.main.huts_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TracksListFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: TracksListViewModelFactory by instance()
    private lateinit var viewModel: TracksListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tracks_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TracksListViewModel::class.java)
        bindRecyclerView()
    }


    private fun bindRecyclerView() = launch {
        val returnedTracks = viewModel.tracks.await()
        returnedTracks.observe(viewLifecycleOwner, Observer {tracks ->
            recycler_view_huts.also {
                it.layoutManager = GridLayoutManager(requireContext(), 2)
                it.setHasFixedSize(true)
                it.adapter =
                    TracksRecyclerAdapter(tracks)
                it.addItemDecoration(
                    GridSpacingItemDecoration(
                        2,
                        20,
                        true
                    )
                )
            }

        })
    }

}
