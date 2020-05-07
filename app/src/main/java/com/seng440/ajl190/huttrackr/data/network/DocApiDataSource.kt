package com.seng440.ajl190.huttrackr.data.network

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.Hut
import com.seng440.ajl190.huttrackr.data.model.HutResponse

interface DocApiDataSource {

    val huts: LiveData<List<HutResponse>>
    val hut: LiveData<Hut>

    suspend fun fetchHuts()

    suspend fun fetchHut(assetId: Int)
}