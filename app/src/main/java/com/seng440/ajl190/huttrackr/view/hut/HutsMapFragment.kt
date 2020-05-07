package com.seng440.ajl190.huttrackr.view.hut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.HutsMapViewModel

class HutsMapFragment : Fragment() {

    companion object {
        fun newInstance() = HutsMapFragment()
    }

    private lateinit var viewModel: HutsMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.huts_map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HutsMapViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
