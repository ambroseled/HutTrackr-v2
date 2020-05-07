package com.seng440.ajl190.huttrackr.view.hut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.view.adpater.HutTabAdapter
import com.seng440.ajl190.huttrackr.viewmodel.HutsViewModel

class HutsFragment : Fragment() {


    private lateinit var viewModel: HutsViewModel
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.huts_fragment, container, false)
        tabLayout = view.findViewById(R.id.hutsTabLayout)
        viewPager = view.findViewById(R.id.hutsTabPager)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HutsViewModel::class.java)


        val tabAdapter = HutTabAdapter(
            requireContext(),
            this.parentFragmentManager
        )
        viewPager.adapter = tabAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

//    override fun onWishListClick(hut: HutResponse) {
//        Toast.makeText(requireContext(), "Wish list toggle for ${hut.name}", Toast.LENGTH_SHORT).show()
//        //todo Implement wish list saving functionality
//    }
//
//    override fun onHutCardClick(hut: HutResponse, view: View) {
//        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.bounce))
//    }
//
//    override fun onMoreInfoClick(hut: HutResponse) {
//        Toast.makeText(requireContext(), "More info for ${hut.name} clicked", Toast.LENGTH_SHORT).show()
//
//        val navController = this.findNavController()
//        val action =
//            HutsFragmentDirections.actionHutsFragmentToHutFragment(
//                hut.assetId
//            )
//        navController.navigate(action)
//    }

}
