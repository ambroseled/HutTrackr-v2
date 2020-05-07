package com.seng440.ajl190.huttrackr.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.utils.NoConnectivityExpection

class DocApiDataSourceImpl(
    private val api: DocApiService
) : DocApiDataSource {

    private val _downloadedCurrentHuts = MutableLiveData<List<HutResponse>>()
    override val downloadedCurrentHuts: LiveData<List<HutResponse>>
        get() = _downloadedCurrentHuts

    override suspend fun fetchHuts() {
        try {
            val fetchedHuts = api.getHuts()
                .await()
            _downloadedCurrentHuts.postValue(fetchedHuts)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }
}