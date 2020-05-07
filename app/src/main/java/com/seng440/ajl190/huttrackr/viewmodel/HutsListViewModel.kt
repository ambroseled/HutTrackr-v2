package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class HutsListViewModel(
    private val hutRepository: HutRepository
) : ViewModel() {

    val huts by lazyDeferred {
        hutRepository.getAllHuts()
    }

}
