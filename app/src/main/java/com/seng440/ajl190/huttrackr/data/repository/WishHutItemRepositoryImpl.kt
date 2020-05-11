package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.WishHutDao
import com.seng440.ajl190.huttrackr.data.model.WishHutItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishHutItemRepositoryImpl(
    private val dao: WishHutDao
) : WishHutItemRepository {


    override suspend fun getWishItems(): LiveData<List<WishHutItem>> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getWishHutItems()
        }
    }

    override fun insertWishItem(wishHutItem: WishHutItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertHutWishItem(wishHutItem)
        }
    }

    override fun deleteWishItem(wishHutItem: WishHutItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.deleteWishHutItem(wishHutItem)
        }
    }
}