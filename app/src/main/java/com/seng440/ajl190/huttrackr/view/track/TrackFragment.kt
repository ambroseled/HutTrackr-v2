package com.seng440.ajl190.huttrackr.view.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.databinding.TrackFragmentBinding
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.viewmodel.TrackViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.TrackViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*

class TrackFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() = TrackFragment()
    }

    override val kodein: Kodein by kodein()
    private val viewModelFactory: TrackViewModelFactory by instance()
    private lateinit var viewModel: TrackViewModel
    private var assetId: String? = ""
    private var _binding: TrackFragmentBinding? = null
    private var isFabOpen: Boolean = false
    private lateinit var mainFab: FloatingActionButton
    private lateinit var visitFab: FloatingActionButton
    private lateinit var wishFab: FloatingActionButton

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TrackFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TrackViewModel::class.java)

        assetId = arguments?.getString("assetId")

        if (assetId != "") {
            bindUi()
        } else {
            // todo handle this case gracefully
        }

        mainFab = activity!!.findViewById(R.id.floatingActionButtonTrack)
        visitFab = activity!!.findViewById(R.id.visitActionButtonTrack)
        wishFab = activity!!.findViewById(R.id.wishActionButtonTrack)

        mainFab.setOnClickListener {
            if (!isFabOpen) {
                openFabMenu()
            } else {
                closeFabMenu()
            }
        }

        visitFab.setOnClickListener {
            addVisit()
            closeFabMenu()
            Toast.makeText(requireContext(), "Visit added", Toast.LENGTH_LONG).show()
        }

        wishFab.setOnClickListener {
            addHutToWishList()
            closeFabMenu()
            Toast.makeText(requireContext(), "Added to wish list", Toast.LENGTH_LONG).show()
        }
    }

    private fun bindUi() = launch {
        viewModel.setTrack(assetId!!)
        val track = viewModel.track.await()
        binding.track = track.value
        //detailedHutImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
    }

    private fun addVisit() = launch {
        val track = viewModel.track.await().value!!
        viewModel.saveVisit(VisitItem(0, track.name, convertRegion(track.region), Date(),
            "track", track.introductionThumbnail, track.assetId))
    }

    private fun addHutToWishList() =launch {
        val track = viewModel.track.await().value!!
        viewModel.saveWish(WishItem(track.assetId, track.name, convertRegion(track.region), "track"))

    }

    private fun closeFabMenu() {
        isFabOpen = false
        visitFab.visibility = View.GONE
        wishFab.visibility = View.GONE
    }

    private fun openFabMenu() {
        isFabOpen = true
        visitFab.visibility = View.VISIBLE
        wishFab.visibility = View.VISIBLE
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
}
