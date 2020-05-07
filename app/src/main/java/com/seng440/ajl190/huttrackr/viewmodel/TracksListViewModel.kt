package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.data.repository.TrackRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class TracksListViewModel(
    private val trackRepository: TrackRepository
) : ViewModel() {

    val tracks by lazyDeferred {
        trackRepository.getTracks()
    }
}
