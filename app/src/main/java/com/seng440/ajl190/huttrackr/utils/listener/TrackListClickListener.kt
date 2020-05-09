package com.seng440.ajl190.huttrackr.utils.listener

import android.view.View
import com.seng440.ajl190.huttrackr.data.model.TrackResponse

interface TrackListClickListener {

    fun onWishListClick(track: TrackResponse)

    fun onTrackCardClick(track: TrackResponse, view: View)

    fun onMoreInfoClick(track: TrackResponse)
}