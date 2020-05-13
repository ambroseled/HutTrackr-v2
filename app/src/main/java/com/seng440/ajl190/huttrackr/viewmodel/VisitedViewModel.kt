package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.data.repository.VisitItemRepository
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class VisitedViewModel(
    private val visitItemRepository: VisitItemRepository,
    private val wishItemRepository: WishItemRepository
) : ViewModel() {
    val visits by lazyDeferred {
        visitItemRepository.getVisitItems()
    }

    fun insertVisitItem(visitItem: VisitItem) {
        visitItemRepository.insertVisitItem(visitItem)
    }

    fun insertWishItem(wishItem: WishItem) {
        wishItemRepository.insertWishItem(wishItem)
    }

    fun deleteWishItem(wishItem: WishItem) {
        wishItemRepository.deleteWishItem(wishItem)
    }

    fun deleteVisitItem(visitItem: VisitItem) {
        visitItemRepository.deleteVisitItem(visitItem)
    }
}
