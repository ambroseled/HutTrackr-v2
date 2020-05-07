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

//    private lateinit var job: Job
//    private val _huts = MutableLiveData<List<HutResponse>>()
//    val allHuts: LiveData<List<HutResponse>>
//        get() = _huts
//
//    fun getHuts() {
//        println("Jeff: in view model getHuts")
//        job = CoroutineHelpers.ioThenMain(
//            {hutRepository.getHuts()},
//            {_huts.value = it?.value}
//        )
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        if (::job.isInitialized) job.cancel()
//    }

}
