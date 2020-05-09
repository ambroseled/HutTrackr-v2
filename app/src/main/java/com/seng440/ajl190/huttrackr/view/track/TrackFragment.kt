package com.seng440.ajl190.huttrackr.view.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.databinding.TrackFragmentBinding
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.viewmodel.TrackViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.TrackViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TrackFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() = TrackFragment()
    }

    override val kodein: Kodein by kodein()
    private val viewModelFactory: TrackViewModelFactory by instance()
    private lateinit var viewModel: TrackViewModel
    private var assetId: String? = ""
    private var _binding: TrackFragmentBinding? = null

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
    }

    private fun bindUi() = launch {
        viewModel.setTrack(assetId!!)
        val track = viewModel.track.await()
        binding.track = track.value
        //detailedHutImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
    }

}
