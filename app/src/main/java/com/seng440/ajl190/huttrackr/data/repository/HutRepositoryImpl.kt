package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.HutDao
import com.seng440.ajl190.huttrackr.data.model.Hut
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
        docApiDataSource.huts.observeForever{ newHuts ->
            saveHuts(newHuts)
        }
    }

    override suspend fun getAllHuts(): LiveData<List<HutResponse>> {
        return withContext(Dispatchers.IO) {
            fetchHutsIfNeeded()
            return@withContext dao.getHuts()
        }
    }

    override suspend fun getHut(assetId: Int): LiveData<Hut> {
        return withContext(Dispatchers.IO) {
            docApiDataSource.fetchHut(assetId)
            return@withContext docApiDataSource.hut
        }
    }

    private suspend fun fetchHutsIfNeeded() {
        //docApiDataSource.fetchHuts()
    }

    private fun saveHuts(fetchedHuts: List<HutResponse>) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertHutResponse(fetchedHuts)
        }
    }

}