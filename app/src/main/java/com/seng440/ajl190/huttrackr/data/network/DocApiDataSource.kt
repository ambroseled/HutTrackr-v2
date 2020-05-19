package com.seng440.ajl190.huttrackr.data.network

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.*

interface DocApiDataSource {

    val huts: LiveData<List<HutResponse>>
    val hut: LiveData<Hut>

    val tracks: LiveData<List<TrackResponse>>
    val track: LiveData<Track>

    val trackAlerts: LiveData<List<TrackAlertResponse>>
    val hutAlerts: LiveData<List<HutAlertResponse>>

    suspend fun fetchHuts()

    suspend fun fetchHut(assetId: Int)

    suspend fun fetchTracks()

    suspend fun fetchTrack(assetId: String)

    suspend fun fetchTrackAlerts(assetId: String)

    suspend fun fetchHutAlerts(assetId: Int)
}