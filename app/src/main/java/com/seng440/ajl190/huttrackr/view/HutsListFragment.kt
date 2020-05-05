package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.api.DocApiService
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.utils.listener.HutListClickListener
import com.seng440.ajl190.huttrackr.view.adpater.HutsRecyclerAdapter
import com.seng440.ajl190.huttrackr.view.decorator.GridSpacingItemDecoration
import com.seng440.ajl190.huttrackr.viewmodel.HutsListViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.HutsListViewModelFactory
import kotlinx.android.synthetic.main.huts_list_fragment.*

class HutsListFragment : Fragment(), HutListClickListener {

    private lateinit var viewModelFactory: HutsListViewModelFactory
    private lateinit var viewModel: HutsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.huts_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = DocApiService()
        val hutsRepository = HutRepository(api)
        viewModelFactory =
            HutsListViewModelFactory(hutsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HutsListViewModel::class.java)
        viewModel.getHuts()
        viewModel.huts.observe(viewLifecycleOwner, Observer {huts ->
            recycler_view_huts.also {
                it.layoutManager = GridLayoutManager(requireContext(), 2)
                it.setHasFixedSize(true)
                it.adapter =
                    HutsRecyclerAdapter(huts, this)
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

    override fun onWishListClick(hut: HutResponse) {
        Toast.makeText(requireContext(), "Wish list toggle for ${hut.name}", Toast.LENGTH_SHORT).show()
        //todo Implement wish list saving functionality
    }

    override fun onHutCardClick(hut: HutResponse, view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.bounce))
    }

    override fun onMoreInfoClick(hut: HutResponse) {
        Toast.makeText(requireContext(), "More info for ${hut.name} clicked", Toast.LENGTH_SHORT).show()

        val navController = this.findNavController()
        val action = HutsFragmentDirections.actionHutsFragmentToHutFragment(hut.assetId)
        navController.navigate(action)
    }



}
