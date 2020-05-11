package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.data.model.WishHutItem
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.data.repository.WishHutItemRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class HutsListViewModel(
    private val hutRepository: HutRepository,
    private val hutWishHutItemRepository: WishHutItemRepository
) : ViewModel() {

    val huts by lazyDeferred {
        hutRepository.getAllHuts()
    }

    fun insertWishHutItem(wishHutItem: WishHutItem) {
        hutWishHutItemRepository.insertWishItem(wishHutItem)
    }

    fun deleteWishHutItem(wishHutItem: WishHutItem) {
        hutWishHutItemRepository.deleteWishItem(wishHutItem)
    }

}
