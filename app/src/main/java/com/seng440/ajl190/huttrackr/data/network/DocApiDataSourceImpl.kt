package com.seng440.ajl190.huttrackr.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seng440.ajl190.huttrackr.data.model.Hut
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.model.Track
import com.seng440.ajl190.huttrackr.data.model.TrackResponse
import com.seng440.ajl190.huttrackr.utils.NoConnectivityExpection

class DocApiDataSourceImpl(
    private val api: DocApiService
) : DocApiDataSource {

    private val _huts = MutableLiveData<List<HutResponse>>()
    override val huts: LiveData<List<HutResponse>>
        get() = _huts

    private val _hut = MutableLiveData<Hut>()
    override val hut: LiveData<Hut>
        get() = _hut

    private val _tracks = MutableLiveData<List<TrackResponse>>()
    override val tracks: LiveData<List<TrackResponse>>
        get() = _tracks

    private val _track = MutableLiveData<Track>()
    override val track: LiveData<Track>
        get() = _track



    override suspend fun fetchHuts() {
        try {
            val fetchedHuts = api.getHutsAsync()
                .await()
            _huts.postValue(fetchedHuts)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchHut(assetId: Int) {
        try {
            val fetchedHut = api.getHutAsync(assetId)
                .await()
            _hut.postValue(fetchedHut)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTracks() {
        try {
            val fetchedTracks = api.getTracksAsync()
                .await()
            _tracks.postValue(fetchedTracks)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTrack(assetId: String) {
        try {
            val fetchedTrack = api.getTrackAsync(assetId)
                .await()
            _track.postValue(fetchedTrack)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }
}