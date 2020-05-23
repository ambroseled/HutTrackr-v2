package com.seng440.ajl190.huttrackr.view.hut

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
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.utils.listener.HutListClickListener
import com.seng440.ajl190.huttrackr.view.adpater.HutsRecyclerAdapter
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.view.decorator.GridSpacingItemDecoration
import com.seng440.ajl190.huttrackr.viewmodel.HutsListViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.HutsListViewModelFactory
import kotlinx.android.synthetic.main.huts_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class HutsListFragment : ScopedFragment(), KodeinAware, HutListClickListener {

    companion object {
        fun newInstance() = HutsListFragment()
    }

    override val kodein: Kodein by kodein()
    private val viewModelFactory: HutsListViewModelFactory by instance()
    private lateinit var viewModel: HutsListViewModel
    private lateinit var adapter: HutsRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.huts_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HutsListViewModel::class.java)

        bindRecyclerList()
    }

    private fun bindRecyclerList() = launch {
        val orientation = requireContext().resources.configuration.orientation
        var gridSize = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridSize = 4
        }
        val returnedHuts = viewModel.huts.await()
        returnedHuts.observe(viewLifecycleOwner, Observer {huts ->
            adapter = HutsRecyclerAdapter(huts, this@HutsListFragment)
            recycler_view_huts.also {
                it.layoutManager = GridLayoutManager(requireContext(), gridSize)
                it.setHasFixedSize(true)
                it.adapter =
                    adapter
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

    override fun onWishListClick(hut: HutResponse, switch: Switch) {
        if (switch.isChecked) {
            viewModel.insertWishHutItem(WishItem(hut.assetId.toString(), hut.name, hut.region, "hut"))
            val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
            tone.startTone(ToneGenerator.TONE_PROP_BEEP)
            tone.release()
        } else {
            viewModel.deleteWishHutItem(WishItem(hut.assetId.toString(), hut.name, hut.region, "hut"))
        }

    }

    override fun onHutCardClick(hut: HutResponse, view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.bounce))
    }

    override fun onMoreInfoClick(hut: HutResponse) {
        val navController = this.findNavController()
        val action = HutsListFragmentDirections.actionHutsListFragmentToHutFragment(hut.assetId)
        navController.navigate(action)
    }

    override fun onPause() {
        super.onPause()
        viewModel.setPosition(15)
    }


}
