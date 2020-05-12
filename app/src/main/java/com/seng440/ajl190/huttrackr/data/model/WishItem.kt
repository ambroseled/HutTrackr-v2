package com.seng440.ajl190.huttrackr.data.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish_item"
//    foreignKeys = [ForeignKey(entity = HutResponse::class,
//    parentColumns = arrayOf("assetId"),
//    childColumns = arrayOf("id"),
//    onDelete = ForeignKey.CASCADE)]
)
data class WishItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "region")
    @Nullable
    val region: String?,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "type")
    val type: String
)

