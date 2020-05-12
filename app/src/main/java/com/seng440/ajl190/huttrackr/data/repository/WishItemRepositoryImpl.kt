package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.dao.WishDao
import com.seng440.ajl190.huttrackr.data.model.WishItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishItemRepositoryImpl(
    private val dao: WishDao
) : WishItemRepository {


    override suspend fun getWishItems(): LiveData<List<WishItem>> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getWishItems()
        }
    }

    override fun insertWishItem(wishItem: WishItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertWishItem(wishItem)
        }
    }

    override fun deleteWishItem(wishItem: WishItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.deleteWishItem(wishItem)
        }
    }
}