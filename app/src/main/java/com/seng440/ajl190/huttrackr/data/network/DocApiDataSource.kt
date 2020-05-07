package com.seng440.ajl190.huttrackr.data.network

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.HutResponse

interface DocApiDataSource {
    val downloadedCurrentHuts: LiveData<List<HutResponse>>

    suspend fun fetchHuts()
}