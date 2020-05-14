package com.seng440.ajl190.huttrackr.data.repository

import com.seng440.ajl190.huttrackr.data.dao.LastFetchDao
import com.seng440.ajl190.huttrackr.data.model.LastFetch

class LastFetchRepositoryImpl(
    private val dao: LastFetchDao
) : LastFetchRepository {
    override suspend fun insertFetch(lastFetch: LastFetch) {
        dao.insertFetch(lastFetch)
    }

    override suspend fun getFetch(type: String): LastFetch {
        return dao.getFetch(type)
    }
}