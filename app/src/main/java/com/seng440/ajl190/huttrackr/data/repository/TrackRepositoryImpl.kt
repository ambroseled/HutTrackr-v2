package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.TrackDao
import com.seng440.ajl190.huttrackr.data.model.TrackResponse
import com.seng440.ajl190.huttrackr.data.network.DocApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrackRepositoryImpl(
    private val dao: TrackDao,
    private val docApiDataSource: DocApiDataSource
) : TrackRepository {

    init {
        docApiDataSource.tracks.observeForever{ newTracks ->
            saveTracks(newTracks)
        }
    }

    override suspend fun getTracks(): LiveData<List<TrackResponse>> {
        return withContext(Dispatchers.IO) {
            /// docApiDataSource.fetchTracks()
            // todo implement periodic fetch of tracks
            return@withContext dao.getTracks()
        }
    }

    private fun saveTracks(tracks: List<TrackResponse>) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertTrack(tracks)
        }
    }
}