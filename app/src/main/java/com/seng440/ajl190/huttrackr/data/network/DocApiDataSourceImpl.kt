package com.seng440.ajl190.huttrackr.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.utils.NoConnectivityExpection

class DocApiDataSourceImpl(
    private val api: DocApiService
) : DocApiDataSource {

    private val _currentHuts = MutableLiveData<List<HutResponse>>()
    override val currentHuts: LiveData<List<HutResponse>>
        get() = _currentHuts


    override suspend fun fetchHuts() {
        try {
            val huts = api.getHuts()
                .await()
            _currentHuts.postValue(huts)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }
}