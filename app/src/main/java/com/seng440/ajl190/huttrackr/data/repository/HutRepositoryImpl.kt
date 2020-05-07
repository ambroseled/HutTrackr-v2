package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.HutDao
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.network.DocApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class HutRepositoryImpl(
    private val dao: HutDao,
    private val docApiDataSource: DocApiDataSource
) : HutRepository {

    init {
        docApiDataSource.downloadedCurrentHuts.observeForever{newHuts ->
            saveHuts(newHuts)
        }
    }

    override suspend fun getAllHuts(): LiveData<List<HutResponse>> {
        return withContext(Dispatchers.IO) {
            initHutsData()
            return@withContext dao.getHuts()
        }
    }

    private fun saveHuts(fetchedHuts: List<HutResponse>) {
        //TODO change this to use an upsert not insert
        GlobalScope.launch(Dispatchers.IO) {
            for (hut in fetchedHuts) {
                dao.insertHut(hut)
            }
        }
    }

    private suspend fun initHutsData() {
        // TODO need to hold last fetch time in the db to use
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusDays(2))) {
            fetchHuts()
        }
    }

    private suspend fun fetchHuts() {
        docApiDataSource.fetchHuts()
    }

    private fun isFetchCurrentNeeded(lastfetchTime: ZonedDateTime): Boolean {
        val oldTime = ZonedDateTime.now().minusDays(1)
        return lastfetchTime.isBefore(oldTime)
    }
}