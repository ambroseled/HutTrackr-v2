package com.seng440.ajl190.huttrackr.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.seng440.ajl190.huttrackr.data.model.Hut
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val API_KEY = "RFNt50W4mUaXZKj1POf7W7107QRNgqG754rYNj9Z"

interface DocApiService {

    @GET("v2/huts")
    @Headers("x-api-key: $API_KEY")
    suspend fun getHuts() : Deferred<List<HutResponse>>

    @GET("v2/huts/{assetId}/detail")
    @Headers("x-api-key: $API_KEY")
    suspend fun getHut(@Path("assetId") assetId: Int): Deferred<Hut>

    companion object {
        operator fun invoke(): DocApiService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("https://api.doc.govt.nz/")
                .build()
                .create(DocApiService::class.java)
        }
    }


}
