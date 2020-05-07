package com.seng440.ajl190.huttrackr.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "track_response")
data class TrackResponse(
    @PrimaryKey
    @ColumnInfo(name = "assetId")
    val assetId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "region")
    val region: List<String>,

    @ColumnInfo(name = "x")
    val x: Double,

    @ColumnInfo(name = "y")
    val y: Double
)