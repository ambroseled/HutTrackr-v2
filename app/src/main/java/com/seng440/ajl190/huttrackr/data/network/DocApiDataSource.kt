package com.seng440.ajl190.huttrackr.data.network

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.Hut
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.model.Track
import com.seng440.ajl190.huttrackr.data.model.TrackResponse

interface DocApiDataSource {

    val huts: LiveData<List<HutResponse>>
    val hut: LiveData<Hut>

    val tracks: LiveData<List<TrackResponse>>
    val track: LiveData<Track>

    suspend fun fetchHuts()

    suspend fun fetchHut(assetId: Int)

    suspend fun fetchTracks()

    suspend fun fetchTrack(assetId: String)
}