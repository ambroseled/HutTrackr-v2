package com.seng440.ajl190.huttrackr.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seng440.ajl190.huttrackr.data.model.*
import com.seng440.ajl190.huttrackr.utils.NoConnectivityException

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

    private val _trackAlerts = MutableLiveData<List<TrackAlertResponse>>()
    override val trackAlerts: LiveData<List<TrackAlertResponse>>
        get() = _trackAlerts

    private val _hutAlerts = MutableLiveData<List<HutAlertResponse>>()
    override val hutAlerts: LiveData<List<HutAlertResponse>>
        get() = _hutAlerts



    override suspend fun fetchHuts() {
        try {
            val fetchedHuts = api.getHutsAsync()
                .await()
            _huts.postValue(fetchedHuts)
        } catch (e: NoConnectivityException) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchHut(assetId: Int) {
        try {
            val fetchedHut = api.getHutAsync(assetId)
                .await()
            _hut.postValue(fetchedHut)
        } catch (e: NoConnectivityException) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTracks() {
        try {
            val fetchedTracks = api.getTracksAsync()
                .await()
            _tracks.postValue(fetchedTracks)
        } catch (e: NoConnectivityException) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTrack(assetId: String) {
        try {
            val fetchedTrack = api.getTrackAsync(assetId)
                .await()
            _track.postValue(fetchedTrack)
        } catch (e: NoConnectivityException) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTrackAlerts(assetId: String) {
        try {
            val fetchedAlerts = api.getTrackAlertsAsync(assetId)
                .await()
            _trackAlerts.postValue(fetchedAlerts)
        } catch (e: NoConnectivityException) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchHutAlerts(assetId: Int) {
        try {
            val fetchedAlerts = api.getHutAlertsAsync(assetId)
                .await()
            _hutAlerts.postValue(fetchedAlerts)
        } catch (e: NoConnectivityException) {
            Log.e("connectivity", "No internet connection", e)
        }
    }
}