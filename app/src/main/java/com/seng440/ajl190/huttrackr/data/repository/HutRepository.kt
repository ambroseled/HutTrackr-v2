package com.seng440.ajl190.huttrackr.data.repository

import com.seng440.ajl190.huttrackr.data.api.DocApiService
import com.seng440.ajl190.huttrackr.data.dao.HutDao

class HutRepository(
    private val apiService: DocApiService,
    private val dao: HutDao
) {

}