package com.seng440.ajl190.huttrackr.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.repository.HutRespository
import com.seng440.ajl190.huttrackr.viewmodel.HutsViewModel

class HutViewModelFactory(private val respository: HutRespository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HutsViewModel(respository) as T
    }
}