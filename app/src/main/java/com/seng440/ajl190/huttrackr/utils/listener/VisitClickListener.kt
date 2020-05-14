package com.seng440.ajl190.huttrackr.utils.listener

import android.view.View
import com.seng440.ajl190.huttrackr.data.model.VisitItem

interface VisitClickListener {

    fun onVisitCardClick(visit: VisitItem, view: View)

    fun onMoreInfoClick(visit: VisitItem)

    fun onDeleteClick(visit: VisitItem)
}