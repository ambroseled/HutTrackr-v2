package com.seng440.ajl190.huttrackr.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.seng440.ajl190.huttrackr.data.converter.DateConverter
import java.util.*

@Entity(tableName = "last_fetch")
@TypeConverters(DateConverter::class)
data class LastFetch(
    @PrimaryKey
    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "date")
    val date: Date
)