package com.seng440.ajl190.huttrackr.utils.api

import retrofit2.Response

abstract class DocApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            //todo handle api exception
            throw Exception(response.code().toString())
        }
    }
}