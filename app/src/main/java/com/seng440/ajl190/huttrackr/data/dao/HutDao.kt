package com.seng440.ajl190.huttrackr.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seng440.ajl190.huttrackr.data.model.HutResponse

@Dao
interface HutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHutResponse(huts: List<HutResponse>)

    @Query("SELECT * FROM hut_response")
    fun getHuts() : LiveData<List<HutResponse>>
}