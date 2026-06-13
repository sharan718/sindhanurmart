package com.example.data.network

import com.squareup.moshi.JsonClass
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

@JsonClass(generateAdapter = true)
data class SignInRequest(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)

@JsonClass(generateAdapter = true)
data class SignInResponse(
    val idToken: String,
    val email: String,
    val refreshToken: String,
    val expiresIn: String,
    val localId: String
)

interface FirebaseAuthApi {
    @POST("v1/accounts:signInWithPassword")
    suspend fun signInWithPassword(
        @Query("key") apiKey: String,
        @Body request: SignInRequest
    ): SignInResponse
}
