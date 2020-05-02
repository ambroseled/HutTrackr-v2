package com.seng440.ajl190.huttrackr.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.repository.HutRepository
import com.seng440.ajl190.huttrackr.viewmodel.HutsListViewModel

class HutsListViewModelFactory(private val repository: HutRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HutsListViewModel(repository) as T
    }
}