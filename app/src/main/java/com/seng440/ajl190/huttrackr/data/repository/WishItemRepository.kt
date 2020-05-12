package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.WishItem

interface WishItemRepository {

    suspend fun getWishItems(): LiveData<List<WishItem>>

    fun insertWishItem(wishItem: WishItem)

    fun deleteWishItem(wishItem: WishItem)
}