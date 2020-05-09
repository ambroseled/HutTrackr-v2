package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.Track
import com.seng440.ajl190.huttrackr.data.model.TrackResponse

interface TrackRepository {

    suspend fun getTracks(): LiveData<List<TrackResponse>>

    suspend fun getTrack(assetId: String): LiveData<Track>
}