package com.seng440.ajl190.huttrackr.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.viewmodel.WishListViewModel

class WishListViewModelFactory(
    private val wishRepository: WishItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WishListViewModel(wishRepository) as T
    }
}