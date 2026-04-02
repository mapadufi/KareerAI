package com.kiko.kareerai.data.remote.jobs

import retrofit2.http.GET
import retrofit2.http.Query

interface JobService {

    @GET("remote-jobs")
    suspend fun buscarVagas(
        @Query("search") query: String
    ): JobResponse
}