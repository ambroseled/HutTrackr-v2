package com.seng440.ajl190.huttrackr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seng440.ajl190.huttrackr.model.Hut
import com.seng440.ajl190.huttrackr.repository.HutRepository
import com.seng440.ajl190.huttrackr.utils.CoroutineHelper
import kotlinx.coroutines.Job

class HutViewModel(
    private val hutRepository: HutRepository
) : ViewModel() {


    private lateinit var job: Job
    private val _hut = MutableLiveData<Hut>()
    val hut: LiveData<Hut>
        get() = _hut

    fun getHut(assetId: Int?) {
        if (assetId != null) {
            job = CoroutineHelper.ioThenMain(
                {hutRepository.getHut(assetId)},
                {_hut.value = it}
            )
        } else {
            // todo handle this
        }

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }


}
