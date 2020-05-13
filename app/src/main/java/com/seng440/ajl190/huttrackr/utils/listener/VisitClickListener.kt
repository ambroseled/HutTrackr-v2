package com.seng440.ajl190.huttrackr.utils.listener

import android.view.View
import android.widget.Switch
import com.seng440.ajl190.huttrackr.data.model.VisitItem

interface VisitClickListener {

    fun onWishListClick(visit: VisitItem, switch: Switch)

    fun onVisitCardClick(visit: VisitItem, view: View)

    fun onMoreInfoClick(visit: VisitItem)
}