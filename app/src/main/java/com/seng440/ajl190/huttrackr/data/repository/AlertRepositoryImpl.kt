package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.HutAlertResponse
import com.seng440.ajl190.huttrackr.data.model.TrackAlertResponse
import com.seng440.ajl190.huttrackr.data.network.DocApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlertRepositoryImpl(
    private val docApiDataSource: DocApiDataSource
    ) : AlertRepository {

    override suspend fun getHutAlerts(assetId: Int): LiveData<List<HutAlertResponse>> {
        return withContext(Dispatchers.IO) {
            docApiDataSource.fetchHutAlerts(assetId)
            return@withContext docApiDataSource.hutAlerts
        }
    }

    override suspend fun getTrackAlerts(assetId: String): LiveData<List<TrackAlertResponse>> {
        return withContext(Dispatchers.IO) {
            docApiDataSource.fetchTrackAlerts(assetId)
            return@withContext docApiDataSource.trackAlerts
        }
    }

}