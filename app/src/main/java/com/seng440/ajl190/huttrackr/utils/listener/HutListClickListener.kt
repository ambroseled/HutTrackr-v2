package com.seng440.ajl190.huttrackr.utils.listener

import android.view.View
import android.widget.Switch
import com.seng440.ajl190.huttrackr.data.model.HutResponse

interface HutListClickListener {

    fun onWishListClick(hut: HutResponse, switch: Switch)

    fun onHutCardClick(hut: HutResponse, view: View)

    fun onMoreInfoClick(hut: HutResponse)
}