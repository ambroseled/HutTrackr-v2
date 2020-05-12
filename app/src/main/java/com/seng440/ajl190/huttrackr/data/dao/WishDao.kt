package com.seng440.ajl190.huttrackr.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.seng440.ajl190.huttrackr.data.model.WishItem

@Dao
interface WishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWishItem(wishItem: WishItem)

    @Query("SELECT * FROM wish_item")
    fun getWishItems() : LiveData<List<WishItem>>

    @Delete
    fun deleteWishItem(wishItem: WishItem)
}