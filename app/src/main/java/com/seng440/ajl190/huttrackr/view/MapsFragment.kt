package com.seng440.ajl190.huttrackr.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.MapsViewModel
import kotlinx.android.synthetic.main.maps_fragment.*

class MapsFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val MY_PERMISSION_FINE_LOCATION = 101

    companion object {
        fun newInstance() = MapsFragment()
    }

    private lateinit var viewModel: MapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.maps_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MapsViewModel::class.java)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationClient.lastLocation.addOnSuccessListener {location ->
            println("Jeff: $location")
            locationView.text = "Lat: ${location.latitude}\nLng: ${location.longitude}"
        }

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSION_FINE_LOCATION)
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSION_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                println("Jeff: location permission given")
            } else {
                //todo handle this
            }
        }
    }


}
