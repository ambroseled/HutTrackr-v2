package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.utils.listener.VisitClickListener
import com.seng440.ajl190.huttrackr.view.adpater.VisitRecyclerAdapter
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.view.decorator.GridSpacingItemDecoration
import com.seng440.ajl190.huttrackr.viewmodel.VisitedViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.VisitViewModelFactory
import kotlinx.android.synthetic.main.visited_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class VisitedFragment : ScopedFragment(), KodeinAware, VisitClickListener {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: VisitViewModelFactory by instance()


    companion object {
        fun newInstance() = VisitedFragment()
    }

    private lateinit var viewModel: VisitedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.visited_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VisitedViewModel::class.java)

        bindRecyclerList()
    }

    private fun bindRecyclerList() = launch {
        val returnedVisits = viewModel.visits.await()
        returnedVisits.observe(viewLifecycleOwner, Observer {visits ->
            recycler_view_visit.also {
                it.layoutManager = GridLayoutManager(requireContext(), 1)
                it.setHasFixedSize(true)
                it.adapter =
                    VisitRecyclerAdapter(visits, this@VisitedFragment)
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

    override fun onVisitCardClick(visit: VisitItem, view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.bounce))
    }

    override fun onMoreInfoClick(visit: VisitItem) {
        val navController = this.findNavController()
        if (visit.type == "hut") {
            val action = VisitedFragmentDirections.actionVisitedFragmentToHutFragment(visit.assetId.toInt())
            navController.navigate(action)
        } else {
            val action = VisitedFragmentDirections.actionVisitedFragmentToTrackFragment(visit.assetId)
            navController.navigate(action)
        }
    }

    override fun onDeleteClick(visit: VisitItem) {
        viewModel.deleteVisitItem(visit)
    }
}
