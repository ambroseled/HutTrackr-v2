package com.seng440.ajl190.huttrackr.data.repository

import androidx.lifecycle.LiveData
import com.seng440.ajl190.huttrackr.data.model.HutResponse

interface HutRepository {

    suspend fun getAllHuts(): LiveData<List<HutResponse>>


    suspend fun getHuts(): LiveData<List<HutResponse>>
}