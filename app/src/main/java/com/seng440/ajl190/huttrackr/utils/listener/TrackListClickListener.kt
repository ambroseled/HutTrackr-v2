package com.seng440.ajl190.huttrackr.utils.listener

import android.view.View
import android.widget.Switch
import com.seng440.ajl190.huttrackr.data.model.TrackResponse

interface TrackListClickListener {

    fun onWishListClick(track: TrackResponse, switch: Switch)

    fun onTrackCardClick(track: TrackResponse, view: View)

    fun onMoreInfoClick(track: TrackResponse)
}