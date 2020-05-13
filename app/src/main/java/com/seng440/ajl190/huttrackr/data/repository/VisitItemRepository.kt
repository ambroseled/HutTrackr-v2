package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.VisitItem

interface VisitItemRepository {

    suspend fun getVisitItems(): LiveData<List<VisitItem>>

    fun insertVisitItem(visitItem: VisitItem)

    fun deleteVisitItem(visitItem: VisitItem)
}