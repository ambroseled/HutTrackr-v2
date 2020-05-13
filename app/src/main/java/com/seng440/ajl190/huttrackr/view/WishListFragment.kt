package com.seng440.ajl190.huttrackr.view

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
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.utils.listener.WishListClickListener
import com.seng440.ajl190.huttrackr.view.adpater.WishRecyclerAdapter
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.view.decorator.GridSpacingItemDecoration
import com.seng440.ajl190.huttrackr.viewmodel.WishListViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.WishListViewModelFactory
import kotlinx.android.synthetic.main.wish_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class WishListFragment : ScopedFragment(), KodeinAware, WishListClickListener {

    companion object {
        fun newInstance() = WishListFragment()
    }

    override val kodein: Kodein by kodein()
    private val viewModelFactory: WishListViewModelFactory by instance()
    private lateinit var viewModel: WishListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wish_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WishListViewModel::class.java)

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
            recycler_view_wish.also {
                it.layoutManager = GridLayoutManager(requireContext(), gridSize)
                it.setHasFixedSize(true)
                it.adapter =
                    WishRecyclerAdapter(huts, this@WishListFragment)
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

    override fun onWishListClick(hut: WishItem, switch: Switch) {
        if (switch.isChecked) {
            viewModel.insertWishHutItem(WishItem(hut.id, hut.name, hut.region, hut.status, "hut"))
            val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
            tone.startTone(ToneGenerator.TONE_PROP_BEEP)
            tone.release()
        } else {
            viewModel.deleteWishHutItem(WishItem(hut.id, hut.name, hut.region, hut.status, "hut"))
        }
    }

    override fun onHutCardClick(hut: WishItem, view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.bounce))
    }

    override fun onMoreInfoClick(hut: WishItem) {
        val navController = this.findNavController()
        val action = WishListFragmentDirections.actionWishListFragmentToHutFragment(hut.id.toInt())
        navController.navigate(action)
    }

}
