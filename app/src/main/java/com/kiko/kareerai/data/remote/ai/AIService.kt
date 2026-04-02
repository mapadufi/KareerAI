package com.kiko.kareerai.data.remote.ai

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AIService {

    @POST("v1beta/models/gemini-1.5-flash:generateContent")
    suspend fun analisarCurriculo(
        @Query("key") apiKey: String,
        @Body request: AIRequest
    ): AIResponse
}
