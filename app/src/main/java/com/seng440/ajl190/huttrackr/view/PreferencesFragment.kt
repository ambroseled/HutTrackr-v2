package com.seng440.ajl190.huttrackr.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.PreferencesViewModel

class PreferencesFragment : PreferenceFragmentCompat() {

    companion object {
        fun newInstance() = PreferencesFragment()
    }

    private lateinit var viewModel: PreferencesViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PreferencesViewModel::class.java)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}
