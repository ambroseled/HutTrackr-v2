package com.seng440.ajl190.huttrackr.view.hut

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.esri.arcgisruntime.geometry.CoordinateFormatter
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReference
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.HutMapViewModel
import kotlinx.android.synthetic.main.hut_map_fragment.*


class HutMapFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val MY_PERMISSION_FINE_LOCATION = 101
    private lateinit var map: MapboxMap
    private lateinit var originLocation: Location
    private lateinit var symbolManager: SymbolManager
    private var sparRef: SpatialReference = SpatialReference.create(2193)


    companion object {
        fun newInstance() = HutMapFragment()
    }

    private lateinit var viewModel: HutMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireContext(), getString(R.string.access_token))
        return inflater.inflate(R.layout.hut_map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HutMapViewModel::class.java)
        mapView?.onCreate(savedInstanceState)
        requestLocationPermissions()
        getLocation()
    }

    private fun getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationClient.lastLocation.addOnSuccessListener {location ->
            originLocation = location
        }
    }

    private fun requestLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSION_FINE_LOCATION)
        }
    }

    private fun initMap(hutLatLng: LatLng)  {
        mapView?.getMapAsync { mapBoxMap ->
            mapBoxMap.setStyle(Style.SATELLITE) {
                symbolManager = SymbolManager(mapView, mapBoxMap, it)
                symbolManager.iconAllowOverlap = true
                symbolManager.iconIgnorePlacement = true
                it.addImage("user", BitmapUtils.getBitmapFromDrawable(resources.getDrawable(R.drawable.ic_user))!!)
                it.addImage("hut", BitmapUtils.getBitmapFromDrawable(resources.getDrawable(R.drawable.ic_home_green_24dp))!!)
                symbolManager.create(
                    SymbolOptions()
                        .withLatLng(LatLng(originLocation.latitude, originLocation.longitude))
                        .withIconImage("user")
                        .withIconSize(.75f))
                symbolManager.create(
                    SymbolOptions()
                        .withLatLng(hutLatLng)
                        .withIconImage("hut")
                        .withIconSize(.75f)
                )
            }
            map = mapBoxMap
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(hutLatLng, 13.0))
        }
    }

    private fun getLatLng(x: Int, y: Int): LatLng {
        val point = Point(x.toDouble(), y.toDouble(), sparRef)
        val strLatLng = CoordinateFormatter.toLatitudeLongitude(point, CoordinateFormatter.LatitudeLongitudeFormat.DECIMAL_DEGREES, 10)
        val coords = strLatLng.split(" ")
        return LatLng(convertStrCoord(coords[0]), convertStrCoord(coords[1]))
    }


    private fun convertStrCoord(coord: String): Double {
        val direction = coord.takeLast(1)
        var doubleVal = coord.dropLast(1).toDouble()
        if (direction == "S" || direction == "W") {
            doubleVal *= -1
        }
        return doubleVal
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSION_FINE_LOCATION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Some features may not work as location services are required", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setAssetCoords(x: Int, y: Int) {
        initMap(getLatLng(x, y))
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }



}
