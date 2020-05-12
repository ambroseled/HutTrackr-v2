package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class WishListViewModel(
    private val hutWishItemRepository: WishItemRepository
) : ViewModel() {

    val huts by lazyDeferred {
        hutWishItemRepository.getWishItems()
    }

    fun insertWishHutItem(wishItem: WishItem) {
        hutWishItemRepository.insertWishItem(wishItem)
    }

    fun deleteWishHutItem(wishItem: WishItem) {
        hutWishItemRepository.deleteWishItem(wishItem)
    }
}
