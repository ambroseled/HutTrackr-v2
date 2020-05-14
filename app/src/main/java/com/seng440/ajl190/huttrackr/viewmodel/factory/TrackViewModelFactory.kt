package com.seng440.ajl190.huttrackr.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seng440.ajl190.huttrackr.data.repository.TrackRepository
import com.seng440.ajl190.huttrackr.data.repository.VisitItemRepository
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.viewmodel.TrackViewModel

class TrackViewModelFactory(
    private val repository: TrackRepository,
    private val visitRepository: VisitItemRepository,
    private val wishItemRepository: WishItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackViewModel(repository, visitRepository, wishItemRepository) as T
    }
}