package com.example.data.repository

import com.example.BuildConfig
import com.example.data.network.FirebaseAuthApi
import com.example.data.network.SignInRequest
import com.example.data.network.SignInResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AuthRepository {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://identitytoolkit.googleapis.com/")
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api = retrofit.create(FirebaseAuthApi::class.java)

    suspend fun login(email: String, password: String): Result<SignInResponse> {
        return try {
            val apiKey = BuildConfig.FIREBASE_WEB_API_KEY
            if (apiKey.isBlank() || apiKey == "YOUR_FIREBASE_API_KEY") {
                return Result.failure(Exception("FIREBASE_WEB_API_KEY is not configured in Secrets panel."))
            }
            val response = api.signInWithPassword(
                apiKey = apiKey,
                request = SignInRequest(email, password)
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
