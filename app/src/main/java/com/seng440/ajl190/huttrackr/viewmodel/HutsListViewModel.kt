package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.utils.CoroutineHelper
import kotlinx.coroutines.Job

class HutsListViewModel(
    private val hutRepository: HutRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _huts = MutableLiveData<List<HutResponse>>()
    val huts: LiveData<List<HutResponse>>
        get() = _huts

    fun getHuts() {
        job = CoroutineHelper.ioThenMain(
            {hutRepository.getHuts()},
            {_huts.value = it}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
