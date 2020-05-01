package com.seng440.ajl190.huttrackr.utils.listener

import android.view.View
import com.seng440.ajl190.huttrackr.model.HutResponse

interface HutListClickListener {

    fun onWishListClick(hut: HutResponse)

    fun onHutCardClick(hut: HutResponse, view: View)

    fun onMoreInfoClick(hut: HutResponse)
}