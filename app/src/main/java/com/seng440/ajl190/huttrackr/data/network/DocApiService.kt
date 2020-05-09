package com.seng440.ajl190.huttrackr.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.seng440.ajl190.huttrackr.data.model.Hut
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.model.Track
import com.seng440.ajl190.huttrackr.data.model.TrackResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

const val API_KEY = "RFNt50W4mUaXZKj1POf7W7107QRNgqG754rYNj9Z"

interface DocApiService {

    @GET("v2/huts")
    @Headers("x-api-key: $API_KEY")
    fun getHutsAsync() : Deferred<List<HutResponse>>

    @GET("v2/huts/{assetId}/detail")
    @Headers("x-api-key: $API_KEY")
    fun getHutAsync(@Path("assetId") assetId: Int): Deferred<Hut>

    @GET("v1/tracks")
    @Headers("x-api-key: $API_KEY")
    fun getTracksAsync(): Deferred<List<TrackResponse>>

    @GET("v1/tracks/{assetId}/detail")
    @Headers("x-api-key: $API_KEY")
    fun getTrackAsync(@Path("assetId") assetId: String): Deferred<Track>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): DocApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("https://api.doc.govt.nz/")
                .build()
                .create(DocApiService::class.java)
        }
    }
}
