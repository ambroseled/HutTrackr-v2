package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.LastFetchDao
import com.seng440.ajl190.huttrackr.data.dao.TrackDao
import com.seng440.ajl190.huttrackr.data.model.LastFetch
import com.seng440.ajl190.huttrackr.data.model.Track
import com.seng440.ajl190.huttrackr.data.model.TrackResponse
import com.seng440.ajl190.huttrackr.data.network.DocApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.*

class TrackRepositoryImpl(
    private val dao: TrackDao,
    private val docApiDataSource: DocApiDataSource,
    private val fetchDao: LastFetchDao
) : TrackRepository {

    init {
        docApiDataSource.tracks.observeForever{ newTracks ->
            saveTracks(newTracks)
        }
    }

    override suspend fun getTracks(): LiveData<List<TrackResponse>> {
        return withContext(Dispatchers.IO) {
            fetchTracksIfNeeded()
            return@withContext dao.getTracks()
        }
    }

    private suspend fun fetchTracksIfNeeded() {
        GlobalScope.launch(Dispatchers.IO) {
            if (dao.getTracks().value.isNullOrEmpty()) {
                docApiDataSource.fetchTracks()
            } else {
                val lastFetch = fetchDao.getFetch("track")
                if (lastFetch.date.time < LocalDate.now().minusDays(5).toEpochDay()) {
                    docApiDataSource.fetchHuts()
                }
            }
        }
    }

    private fun saveTracks(tracks: List<TrackResponse>) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertTrackResponse(tracks)
            fetchDao.insertFetch(LastFetch("track", Date()))
        }
    }

    override suspend fun getTrack(assetId: String): LiveData<Track> {
        return withContext(Dispatchers.IO) {
            docApiDataSource.fetchTrack(assetId)
            return@withContext docApiDataSource.track
        }
    }
}