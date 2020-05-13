package com.seng440.ajl190.huttrackr.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.seng440.ajl190.huttrackr.data.model.VisitItem

@Dao
interface VisitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVisitItem(visitItem: VisitItem)

    @Query("SELECT * FROM visit_item")
    fun getVisitItems() : LiveData<List<VisitItem>>

    @Delete
    fun deleteVisitItem(visitItem: VisitItem)
}