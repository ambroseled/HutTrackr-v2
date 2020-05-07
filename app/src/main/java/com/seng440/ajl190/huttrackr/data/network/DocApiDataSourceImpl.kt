package com.seng440.ajl190.huttrackr.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seng440.ajl190.huttrackr.data.model.Hut
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.utils.NoConnectivityExpection

class DocApiDataSourceImpl(
    private val api: DocApiService
) : DocApiDataSource {

    private val _huts = MutableLiveData<List<HutResponse>>()
    override val huts: LiveData<List<HutResponse>>
        get() = _huts

    private val _hut = MutableLiveData<Hut>()
    override val hut: LiveData<Hut>
        get() = _hut



    override suspend fun fetchHuts() {
        try {
            val fetchedHuts = api.getHuts()
                .await()
            _huts.postValue(fetchedHuts)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchHut(assetId: Int) {
        try {
            val fetchedHut = api.getHut(assetId)
                .await()
            _hut.postValue(fetchedHut)
        } catch (e: NoConnectivityExpection) {
            Log.e("connectivity", "No internet connection", e)
        }
    }
}