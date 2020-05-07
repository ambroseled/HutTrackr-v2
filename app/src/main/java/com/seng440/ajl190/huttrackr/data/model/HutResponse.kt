package com.seng440.ajl190.huttrackr.data.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hut_response")
data class HutResponse(
    @PrimaryKey
    @ColumnInfo(name = "assetId")
    val assetId: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "region")
    @Nullable
    val region: String?,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "x")
    val x: Int,

    @ColumnInfo(name = "y")
    val y: Int
)