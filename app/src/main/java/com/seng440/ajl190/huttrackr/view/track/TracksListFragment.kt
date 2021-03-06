package com.seng440.ajl190.huttrackr.view.track

import android.content.res.Configuration
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Switch
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.TrackResponse
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.utils.listener.TrackListClickListener
import com.seng440.ajl190.huttrackr.view.adpater.TracksRecyclerAdapter
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.view.decorator.GridSpacingItemDecoration
import com.seng440.ajl190.huttrackr.viewmodel.TracksListViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.TracksListViewModelFactory
import kotlinx.android.synthetic.main.tracks_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TracksListFragment : ScopedFragment(), KodeinAware, TrackListClickListener {

    companion object {
        fun newInstance() = TracksListFragment()
    }

    override val kodein: Kodein by kodein()
    private val viewModelFactory: TracksListViewModelFactory by instance()
    private lateinit var viewModel: TracksListViewModel
    private lateinit var adapter: TracksRecyclerAdapter

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
        val orientation = requireContext().resources.configuration.orientation
        var gridSize = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridSize = 4
        }

        val returnedTracks = viewModel.tracks.await()
        returnedTracks.observe(viewLifecycleOwner, Observer {tracks ->
            adapter = TracksRecyclerAdapter(tracks, this@TracksListFragment)
            recycler_view_tracks.also {
                it.layoutManager = GridLayoutManager(requireContext(), gridSize)
                it.setHasFixedSize(true)
                it.adapter = adapter
                it.addItemDecoration(
                    GridSpacingItemDecoration(
                        2,
                        20,
                        true
                    )
                )
                it.scrollToPosition(viewModel.getPosition())
            }

        })
    }

    override fun onTrackCardClick(track: TrackResponse, view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.bounce))

    }

    override fun onMoreInfoClick(track: TrackResponse) {
        val navController = this.findNavController()
        val action = TracksListFragmentDirections.actionTracksListFragmentToTrackFragment(track.assetId)
        navController.navigate(action)
    }

    override fun onWishListClick(track: TrackResponse, switch: Switch) {
        if (switch.isChecked) {
            viewModel.insertWishHutItem(WishItem(track.assetId, track.name, convertRegion(track.region), "track"))
            val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
            tone.startTone(ToneGenerator.TONE_PROP_BEEP)
            tone.release()
        } else {
            viewModel.deleteWishHutItem(WishItem(track.assetId, track.name, convertRegion(track.region),  "track"))
        }
    }

    /**
     * Function the list of regions in a track to a string
     */
    private fun convertRegion(regions: List<String>): String {
        var outputRegions = ""
        for (region in regions) {
            if (outputRegions != "") {
                outputRegions += ", "
            }
            outputRegions += region
        }
        return outputRegions
    }

    override fun onPause() {
        super.onPause()
        viewModel.setPosition(adapter.currentPos)
    }

}
