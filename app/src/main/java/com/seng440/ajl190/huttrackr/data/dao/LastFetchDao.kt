package com.seng440.ajl190.huttrackr.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seng440.ajl190.huttrackr.data.model.LastFetch

@Dao
interface LastFetchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFetch(lastFetch: LastFetch)

    @Query("SELECT * FROM last_fetch WHERE type = :fetchType")
    fun getFetch(fetchType: String) : LastFetch
}