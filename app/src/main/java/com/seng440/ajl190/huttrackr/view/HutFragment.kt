package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.databinding.HutFragmentBinding
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.viewmodel.HutViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.HutViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class HutFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: HutViewModelFactory by instance()
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(HutViewModel::class.java)
        assetId = arguments?.getInt("assetId")

        if (assetId != -1) {
            bindUi()
        } else {
            // todo handle this case gracefully
        }
    }

    private fun bindUi() = launch {
        viewModel.setHut(assetId!!)
    }

}
