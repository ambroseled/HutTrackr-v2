package com.seng440.ajl190.huttrackr.data.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish_hut_item"
//    foreignKeys = [ForeignKey(entity = HutResponse::class,
//    parentColumns = arrayOf("assetId"),
//    childColumns = arrayOf("hutId"),
//    onDelete = ForeignKey.CASCADE)]
)
data class WishHutItem(
    @PrimaryKey
    @ColumnInfo(name = "hut_id")
    val hutId: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "region")
    @Nullable
    val region: String?,

    @ColumnInfo(name = "status")
    val status: String
    )

