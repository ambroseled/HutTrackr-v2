package com.seng440.ajl190.huttrackr.utils.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.repository.HutRepository
import com.seng440.ajl190.huttrackr.viewmodel.HutsViewModel

class HutsViewModelFactory(private val repository: HutRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HutsViewModel(repository) as T
    }
}