package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class HutsListViewModel(
    private val hutRepository: HutRepository,
    private val hutWishItemRepository: WishItemRepository
) : ViewModel() {

    private var listPosition = 0

    val huts by lazyDeferred {
        hutRepository.getAllHuts()
    }

    fun insertWishHutItem(wishItem: WishItem) {
        hutWishItemRepository.insertWishItem(wishItem)
    }

    fun deleteWishHutItem(wishItem: WishItem) {
        hutWishItemRepository.deleteWishItem(wishItem)
    }

    fun getPosition(): Int {
        return listPosition
    }

    fun setPosition(pos: Int) {
        listPosition = pos
    }

}
