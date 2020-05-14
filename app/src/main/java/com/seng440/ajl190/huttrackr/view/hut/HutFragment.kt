package com.seng440.ajl190.huttrackr.view.hut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.databinding.HutFragmentBinding
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.viewmodel.HutViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.HutViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*


class HutFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() = HutFragment()
    }

    override val kodein: Kodein by kodein()
    private val viewModelFactory: HutViewModelFactory by instance()
    private lateinit var viewModel: HutViewModel
    private var assetId: Int? = -1
    private var _binding: HutFragmentBinding? = null
    private var isFabOpen: Boolean = false
    private lateinit var mainFab: FloatingActionButton
    private lateinit var visitFab: FloatingActionButton
    private lateinit var wishFab: FloatingActionButton

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

        mainFab = activity!!.findViewById(R.id.floatingActionButton)
        visitFab = activity!!.findViewById(R.id.visitActionButton)
        wishFab = activity!!.findViewById(R.id.wishActionButton)

        mainFab.setOnClickListener {
            if (!isFabOpen) {
                openFabMenu()
            } else {
                closeFabMenu()
            }
        }

        visitFab.setOnClickListener {
            addVisit()
            closeFabMenu()
            Toast.makeText(requireContext(), "Visit added", Toast.LENGTH_LONG).show()
        }

        wishFab.setOnClickListener {
            addHutToWishList()
            closeFabMenu()
            Toast.makeText(requireContext(), "Added to wish list", Toast.LENGTH_LONG).show()
        }
    }

    private fun addVisit() = launch {
        val hut = viewModel.hut.await().value!!
        viewModel.saveVisit(VisitItem(0, hut.name, hut.region, Date(),"hut", hut.introductionThumbnail, hut.assetId.toString()))
    }

    private fun addHutToWishList() =launch {
        val hut = viewModel.hut.await().value!!
        viewModel.saveWish(WishItem(hut.assetId.toString(), hut.name, hut.region, "hut"))

    }

    private fun closeFabMenu() {
        isFabOpen = false
        visitFab.visibility = View.GONE
        wishFab.visibility = View.GONE
    }

    private fun openFabMenu() {
        isFabOpen = true
        visitFab.visibility = View.VISIBLE
        wishFab.visibility = View.VISIBLE
    }

    private fun bindUi() = launch {
        viewModel.setHut(assetId!!)
        val hut = viewModel.hut.await()
        binding.hut = hut.value
    }

}
