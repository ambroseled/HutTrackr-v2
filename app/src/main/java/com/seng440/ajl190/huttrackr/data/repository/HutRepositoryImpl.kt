package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.HutDao
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.network.DocApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HutRepositoryImpl(
    private val dao: HutDao,
    private val docApiDataSource: DocApiDataSource
) : HutRepository {

    init {
        docApiDataSource.currentHuts.observeForever{newHuts ->
            saveHuts(newHuts)
        }
    }

    override suspend fun getHuts(): LiveData<List<HutResponse>> {
        val fromDb = dao.getHuts()
        return fromDb
    }

    override suspend fun getAllHuts(): LiveData<List<HutResponse>> {
        return withContext(Dispatchers.IO) {
            fetchHutsIfNeeded()
            return@withContext dao.getHuts()
        }
    }

    private suspend fun fetchHutsIfNeeded() {
        docApiDataSource.fetchHuts()
    }

    private fun saveHuts(fetchedHuts: List<HutResponse>) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertHut(fetchedHuts)
        }
    }

}