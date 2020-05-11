package com.seng440.ajl190.huttrackr.view.hut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.seng440.ajl190.huttrackr.R
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

    companion object {
        fun newInstance() = HutFragment()
    }

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

        // todo this logic will be used for notifications
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val notifications = sharedPreferences.getBoolean("notifications", true)
        Toast.makeText(requireContext(), "Pref value: $notifications", Toast.LENGTH_SHORT).show()
    }

    private fun bindUi() = launch {
        viewModel.setHut(assetId!!)
        val hut = viewModel.hut.await()
        activity?.findViewById<Toolbar>(R.id.toolBar)?.title = hut.value?.name
        binding.hut = hut.value
        //detailedHutImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
    }

}
