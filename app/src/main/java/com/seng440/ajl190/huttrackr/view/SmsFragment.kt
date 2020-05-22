package com.seng440.ajl190.huttrackr.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.viewmodel.SmsViewModel
import kotlinx.android.synthetic.main.sms_fragment.*


class SmsFragment : DialogFragment() {

    private val PERMISSION_SEND_SMS = 123
    private lateinit var smsManager: SmsManager
    private lateinit var docLink: String

    companion object {
        fun newInstance() = SmsFragment()
    }

    private lateinit var viewModel: SmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sms_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SmsViewModel::class.java)
        smsManager = SmsManager.getDefault()
        requestSmsPermission()

        docLink = arguments!!.getString("docLink")!!
        sendSmsButton.setOnClickListener {
            sendSms()
            dialog?.dismiss()
        }

    }

    private fun sendSms() {
        smsManager.sendTextMessage(phoneNumberVal.text.toString(), null, "${smsMessageVal.text}\n\n$docLink", null, null)
    }


    private fun requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.SEND_SMS), PERMISSION_SEND_SMS
            )
        }
    }

}
