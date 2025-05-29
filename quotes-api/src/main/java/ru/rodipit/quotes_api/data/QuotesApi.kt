package ru.rodipit.quotes_api.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.rodipit.quotes_api.data.dto.AuthResponse
import ru.rodipit.quotes_api.data.dto.AuthUserData
import ru.rodipit.quotes_api.data.dto.QuoteDto

internal interface QuotesApi {

    @GET("search")
    suspend fun search(
        @Query("query") query: String,
    ): List<QuoteDto>

    @GET("quote")
    suspend fun quote(
        @Query("id") id: String,
    ): QuoteDto

    @GET("feed")
    suspend fun feed(): List<QuoteDto>

    @POST("register")
    suspend fun register(@Body authUserData: AuthUserData): AuthResponse

    @POST("login")
    suspend fun login(@Body authUserData: AuthUserData): AuthResponse

}