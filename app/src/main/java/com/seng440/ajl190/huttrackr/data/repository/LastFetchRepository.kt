package com.seng440.ajl190.huttrackr.data.repository

import com.seng440.ajl190.huttrackr.data.model.LastFetch

interface LastFetchRepository {

    suspend fun insertFetch(lastFetch: LastFetch)

    suspend fun getFetch(type: String): LastFetch
}