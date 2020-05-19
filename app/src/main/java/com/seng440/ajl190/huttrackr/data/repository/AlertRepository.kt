package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.HutAlertResponse
import com.seng440.ajl190.huttrackr.data.model.TrackAlertResponse

interface AlertRepository {

    suspend fun getHutAlerts(assetId: Int): LiveData<List<HutAlertResponse>>

    suspend fun getTrackAlerts(assetId: String): LiveData<List<TrackAlertResponse>>
}