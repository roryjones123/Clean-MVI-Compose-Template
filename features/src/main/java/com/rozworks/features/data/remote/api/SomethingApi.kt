package com.rozworks.features.data.remote.api

import com.rozworks.features.data.remote.model.SomethingResponseObject
import retrofit2.http.GET

interface SomethingApi {
    @GET("somethings")
    suspend fun getSomethings(): List<SomethingResponseObject>
}
