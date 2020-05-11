package com.seng440.ajl190.huttrackr.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.seng440.ajl190.huttrackr.data.model.WishHutItem

@Dao
interface WishHutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHutWishItem(wishItem: WishHutItem)

    @Query("SELECT * FROM wish_hut_item")
    fun getWishHutItems() : LiveData<List<WishHutItem>>

    @Delete
    fun deleteWishHutItem(wishItem: WishHutItem)
}