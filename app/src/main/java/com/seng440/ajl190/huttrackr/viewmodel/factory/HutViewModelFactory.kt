package com.seng440.ajl190.huttrackr.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.data.repository.AlertRepository
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.data.repository.VisitItemRepository
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.viewmodel.HutViewModel

class HutViewModelFactory(
    private val repository: HutRepository,
    private val visitItemRepository: VisitItemRepository,
    private val wishItemRepository: WishItemRepository,
    private val alertRepository: AlertRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HutViewModel(repository, visitItemRepository, wishItemRepository, alertRepository) as T
    }
}