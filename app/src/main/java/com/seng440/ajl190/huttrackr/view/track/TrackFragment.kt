package com.seng440.ajl190.huttrackr.view.track

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.databinding.TrackFragmentBinding
import com.seng440.ajl190.huttrackr.view.SmsFragment
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.viewmodel.TrackViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.TrackViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*

class TrackFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() = TrackFragment()
    }

    override val kodein: Kodein by kodein()
    private val viewModelFactory: TrackViewModelFactory by instance()
    private lateinit var viewModel: TrackViewModel
    private var assetId: String? = ""
    private var _binding: TrackFragmentBinding? = null
    private var isFabOpen: Boolean = false
    private lateinit var mainFab: FloatingActionButton
    private lateinit var visitFab: FloatingActionButton
    private lateinit var wishFab: FloatingActionButton
    private lateinit var smsFab: FloatingActionButton
    private lateinit var notificationManager: NotificationManager
    private val channelId = "com.seng440.ajl190.hutTrackr"
    private lateinit var trackLink: String

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TrackFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TrackViewModel::class.java)
        notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        assetId = arguments?.getString("assetId")

        if (assetId != "") {
            bindUi()
            checkForAlerts()
        } else {
            Toast.makeText(requireContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show()
        }

        mainFab = activity!!.findViewById(R.id.floatingActionButtonTrack)
        visitFab = activity!!.findViewById(R.id.visitActionButtonTrack)
        wishFab = activity!!.findViewById(R.id.wishActionButtonTrack)
        smsFab = activity!!.findViewById(R.id.smsActionButtonTrack)

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

        smsFab.setOnClickListener {
            closeFabMenu()
            val smsDialog = SmsFragment()
            val bundle = Bundle()
            bundle.putString("docLink", trackLink)
            smsDialog.arguments = bundle
            smsDialog.show(activity!!.supportFragmentManager, "SmsDialog")
        }
    }

    private fun bindUi() = launch {
        viewModel.setTrack(assetId!!)
        val track = viewModel.track.await()
        binding.track = track.value
        trackLink = track.value!!.staticLink
    }

    private fun addVisit() = launch {
        val track = viewModel.track.await().value!!
        viewModel.saveVisit(VisitItem(0, track.name, convertRegion(track.region), Date(),
            "track", track.introductionThumbnail, track.assetId))
    }

    private fun addHutToWishList() =launch {
        val track = viewModel.track.await().value!!
        viewModel.saveWish(WishItem(track.assetId, track.name, convertRegion(track.region), "track"))

    }

    private fun closeFabMenu() {
        isFabOpen = false
        visitFab.visibility = View.GONE
        wishFab.visibility = View.GONE
        smsFab.visibility = View.GONE
    }

    private fun openFabMenu() {
        isFabOpen = true
        visitFab.visibility = View.VISIBLE
        wishFab.visibility = View.VISIBLE
        smsFab.visibility = View.VISIBLE
    }

    /**
     * Function the list of regions in a track to a string
     */
    private fun convertRegion(regions: List<String>): String {
        var outputRegions = ""
        for (region in regions) {
            if (outputRegions != "") {
                outputRegions += ", "
            }
            outputRegions += region
        }
        return outputRegions
    }

    private fun checkForAlerts() = launch {
        val alerts = viewModel.alerts.await()
        val track = viewModel.track.await()
        if (alerts.value != null) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val notifications = sharedPreferences.getBoolean("notifications", true)
            if (notifications && alerts.value!!.isNotEmpty()) {
                var id = 1
                for (alert in alerts.value!![0].alerts) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val name = "not_name"
                        val descriptionText = "not_description"
                        val importance = NotificationManager.IMPORTANCE_DEFAULT
                        val channel = NotificationChannel(channelId, name, importance).apply {
                            description = descriptionText
                        }
                        notificationManager.createNotificationChannel(channel)

                        val builder = NotificationCompat.Builder(requireContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Alert for ${track.value?.name}")
                            .setContentText(alert.heading)
                            .setStyle(NotificationCompat.BigTextStyle()
                                .bigText("${alert.heading}\nFor more information follow the link to DoC's website from the hut page"))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            // Set the intent that will fire when the user taps the notification
                            .setAutoCancel(true)

                        with(NotificationManagerCompat.from(requireContext())) {
                            // notificationId is a unique int for each notification that you must define
                            notify(id, builder.build())
                        }
                        id += 1
                    }
                }

            }
        }
    }
}
