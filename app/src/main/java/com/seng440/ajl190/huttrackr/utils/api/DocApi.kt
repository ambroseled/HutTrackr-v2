package com.seng440.ajl190.huttrackr.utils.api

import com.seng440.ajl190.huttrackr.model.HutResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface DocApi {

    @GET("v2/huts")
    @Headers("x-api-key: RFNt50W4mUaXZKj1POf7W7107QRNgqG754rYNj9Z")
    suspend fun getHuts() : Response<List<HutResponse>>

    companion object {
        operator fun invoke(): DocApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.doc.govt.nz/")
                .build()
                .create(DocApi::class.java)
        }
    }


}
