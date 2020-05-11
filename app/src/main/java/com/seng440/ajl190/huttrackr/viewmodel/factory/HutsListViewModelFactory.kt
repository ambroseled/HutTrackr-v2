package com.seng440.ajl190.huttrackr.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.data.repository.WishHutItemRepository
import com.seng440.ajl190.huttrackr.viewmodel.HutsListViewModel

class HutsListViewModelFactory(
    private val hutRepository: HutRepository,
    private val wishRepository: WishHutItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HutsListViewModel(hutRepository, wishRepository) as T
    }
}