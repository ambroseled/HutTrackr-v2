package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.PreferencesViewModel

class PreferencesFragment : Fragment() {

    companion object {
        fun newInstance() = PreferencesFragment()
    }

    private lateinit var viewModel: PreferencesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.preferences_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PreferencesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
