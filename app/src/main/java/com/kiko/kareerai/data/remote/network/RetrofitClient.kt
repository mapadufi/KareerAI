package com.kiko.kareerai.data.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // ⚠️ Obtenha sua chave gratuita em: https://aistudio.google.com/
    private const val GEMINI_API_KEY = "SUA_CHAVE_GEMINI_GRATUITA"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private const val BASE_URL_AI = "https://generativelanguage.googleapis.com/"
    private const val BASE_URL_JOBS = "https://remotive.com/api/"

    fun getRetrofitAI(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_AI)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitJobs(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_JOBS)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    fun getApiKey(): String = GEMINI_API_KEY
}
