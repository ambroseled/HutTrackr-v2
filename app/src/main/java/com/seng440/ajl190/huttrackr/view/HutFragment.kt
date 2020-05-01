package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.seng440.ajl190.huttrackr.viewmodel.HutViewModel
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.repository.HutRepository
import com.seng440.ajl190.huttrackr.utils.api.DocApi
import com.seng440.ajl190.huttrackr.utils.factory.HutViewModelFactory
import kotlinx.android.synthetic.main.hut_fragment.*


class HutFragment : Fragment() {

    companion object {
        fun newInstance() = HutFragment()
    }

    private lateinit var viewModelFactory: HutViewModelFactory
    private lateinit var viewModel: HutViewModel
    private var assetId: Int? = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hut_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = DocApi()
        val repository = HutRepository(api)
        val viewModelFactory = HutViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HutViewModel::class.java)
        assetId = arguments?.getInt("assetId")

        if (assetId != -1) {
            viewModel.getHut(assetId)
            viewModel.hut.observe(viewLifecycleOwner, Observer { hut ->
                detailedHutName.text = hut.name
            })
        } else {
            // todo handle this case gracefully
        }
    }

}
