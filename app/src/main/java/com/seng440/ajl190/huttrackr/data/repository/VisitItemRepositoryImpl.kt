package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.VisitDao
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VisitItemRepositoryImpl(
    private val dao: VisitDao
) : VisitItemRepository {
    override suspend fun getVisitItems(): LiveData<List<VisitItem>> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getVisitItems()
        }
    }

    override fun insertVisitItem(visitItem: VisitItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertVisitItem(visitItem)
        }
    }

    override fun deleteVisitItem(visitItem: VisitItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.deleteVisitItem(visitItem)
        }
    }
}