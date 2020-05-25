package com.seng440.ajl190.huttrackr.view.hut

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.Uri
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
import com.seng440.ajl190.huttrackr.databinding.HutFragmentBinding
import com.seng440.ajl190.huttrackr.view.SmsFragment
import com.seng440.ajl190.huttrackr.view.base.ScopedFragment
import com.seng440.ajl190.huttrackr.viewmodel.HutViewModel
import com.seng440.ajl190.huttrackr.viewmodel.factory.HutViewModelFactory
import kotlinx.android.synthetic.main.hut_fragment.*
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
    private lateinit var smsFab: FloatingActionButton
    private lateinit var notificationManager: NotificationManager
    private val channelId = "com.seng440.ajl190.hutTrackr"
    private lateinit var hutLink: String
    private lateinit var mapFragment: HutMapFragment
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HutFragmentBinding.inflate(layoutInflater, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.hutMapFragment) as HutMapFragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HutViewModel::class.java)
        assetId = arguments?.getInt("assetId")
        notificationManager = activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (assetId != -1) {
            bindUi()
            checkForAlerts()
        } else {
            Toast.makeText(requireContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show()
        }
        initFabs()
        hutDocLink.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(hutLink)))
        }
    }

    private fun initFabs() {
        mainFab = activity!!.findViewById(R.id.floatingActionButton)
        visitFab = activity!!.findViewById(R.id.visitActionButton)
        wishFab = activity!!.findViewById(R.id.wishActionButton)
        smsFab = activity!!.findViewById(R.id.smsActionButton)

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
            bundle.putString("docLink", hutLink)
            smsDialog.arguments = bundle
            smsDialog.show(activity!!.supportFragmentManager, "SmsDialog")
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
        smsFab.visibility = View.GONE
    }

    private fun openFabMenu() {
        isFabOpen = true
        visitFab.visibility = View.VISIBLE
        wishFab.visibility = View.VISIBLE
        smsFab.visibility = View.VISIBLE
    }

    private fun bindUi() = launch {
        viewModel.setHut(assetId!!)
        val hut = viewModel.hut.await()
        binding.hut = hut.value
        hutLink = hut.value!!.staticLink
        mapFragment.setAssetCoords(hut.value!!.x, hut.value!!.y)
    }

    private fun checkForAlerts() = launch {
        val alerts = viewModel.alerts.await()
        val hut = viewModel.hut.await()
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
                            .setContentTitle("Alert for ${hut.value?.name}")
                            .setContentText(alert.heading)
                            .setStyle(NotificationCompat.BigTextStyle()
                                .bigText("${alert.heading}\nFor more information follow the link to DoC's website from the hut page"))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(true)

                        with(NotificationManagerCompat.from(requireContext())) {
                            notify(id, builder.build())
                        }
                        id += 1
                    }
                }
            }
        }
    }


}
