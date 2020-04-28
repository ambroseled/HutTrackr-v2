package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.repository.HutRespository
import com.seng440.ajl190.huttrackr.utils.DocApi
import com.seng440.ajl190.huttrackr.utils.GridSpacingItemDecoration
import com.seng440.ajl190.huttrackr.utils.HutRecyclerAdapter
import com.seng440.ajl190.huttrackr.utils.HutViewModelFactory
import com.seng440.ajl190.huttrackr.viewmodel.HutsViewModel
import kotlinx.android.synthetic.main.huts_fragment.*

class HutsFragment : Fragment() {

    private lateinit var viewModelFactory: HutViewModelFactory
    private lateinit var viewModel: HutsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.huts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = DocApi()
        val hutsRespository = HutRespository(api)
        viewModelFactory = HutViewModelFactory(hutsRespository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HutsViewModel::class.java)
        viewModel.getHuts()
        viewModel.huts.observe(viewLifecycleOwner, Observer {huts ->
            recycler_view_huts.also {
                it.layoutManager = GridLayoutManager(requireContext(), 2)
                it.setHasFixedSize(true)
                it.adapter = HutRecyclerAdapter(huts)
                it.addItemDecoration( GridSpacingItemDecoration(2, 20, true))
            }

        })
    }


}
