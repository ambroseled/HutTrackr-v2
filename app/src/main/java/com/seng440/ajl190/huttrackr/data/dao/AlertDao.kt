package com.seng440.ajl190.huttrackr.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlerts(alerts: List<DisplayAlert>)

    @Query("SELECT * FROM display_alert")
    fun getAllAlerts() : LiveData<List<DisplayAlert>>

    @Query("SELECT * FROM display_alert WHERE type = :alertType")
    fun getAllHutAlerts(alertType: String) : LiveData<List<DisplayAlert>>

    @Query("SELECT * FROM display_alert WHERE type = :alertType")
    fun getAllTrackAlerts(alertType: String) : LiveData<List<DisplayAlert>>

    @Query("DELETE FROM display_alert WHERE asset_id = :assetId")
    fun deleteAlertsFor(assetId: String)

    @Query("SELECT * FROM display_alert WHERE asset_id = :assetId")
    fun getAlertsFor(assetId: String): LiveData<List<DisplayAlert>>
}