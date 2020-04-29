package com.seng440.ajl190.huttrackr.repository

import com.seng440.ajl190.huttrackr.utils.DocApi
import com.seng440.ajl190.huttrackr.utils.DocApiRequest

class HutRepository(
    private val api: DocApi
) : DocApiRequest() {

    suspend fun getHuts() = apiRequest {api.getHuts()}
}