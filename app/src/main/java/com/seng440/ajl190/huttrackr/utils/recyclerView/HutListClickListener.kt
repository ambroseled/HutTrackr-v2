package com.seng440.ajl190.huttrackr.utils.recyclerView

import android.view.View
import com.seng440.ajl190.huttrackr.model.HutResponse

interface HutListClickListener {

    fun onWishListClick(hut: HutResponse)

    fun onHutCardClick(hut: HutResponse, view: View)

    fun onMoreInfoClick(hut: HutResponse)
}