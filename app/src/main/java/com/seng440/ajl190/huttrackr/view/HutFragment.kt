package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.viewmodel.HutViewModel
import com.seng440.ajl190.huttrackr.databinding.HutFragmentBinding
import com.seng440.ajl190.huttrackr.repository.HutRepository
import com.seng440.ajl190.huttrackr.utils.api.DocApi
import com.seng440.ajl190.huttrackr.utils.factory.HutViewModelFactory


class HutFragment : Fragment() {

    private lateinit var viewModelFactory: HutViewModelFactory
    private lateinit var viewModel: HutViewModel
    private var assetId: Int? = -1
    private var _binding: HutFragmentBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HutFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = DocApi()
        val repository = HutRepository(api)
        viewModelFactory = HutViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HutViewModel::class.java)
        assetId = arguments?.getInt("assetId")

        if (assetId != -1) {
            viewModel.getHut(assetId)
            viewModel.hut.observe(viewLifecycleOwner, Observer { hut ->
                binding.hut = hut
            })
        } else {
            // todo handle this case gracefully
        }
    }

}
