package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.WishHutItem

interface WishHutItemRepository {

    suspend fun getWishItems(): LiveData<List<WishHutItem>>

    fun insertWishItem(wishHutItem: WishHutItem)

    fun deleteWishItem(wishHutItem: WishHutItem)
}