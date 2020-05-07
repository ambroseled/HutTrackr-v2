package com.seng440.ajl190.huttrackr.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seng440.ajl190.huttrackr.data.model.TrackResponse

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(tracks: List<TrackResponse>)

    @Query("SELECT * FROM track_response")
    fun getTracks() : LiveData<List<TrackResponse>>
}