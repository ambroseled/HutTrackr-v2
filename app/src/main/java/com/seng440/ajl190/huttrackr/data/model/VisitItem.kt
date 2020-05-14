package com.seng440.ajl190.huttrackr.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visit_item")
data class VisitItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "visit_id")
    val visitId: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "region")
    val region: String,

//    @ColumnInfo(name = "date")
//    val date: Date,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "asset_id")
    val assetId: String
)