package ru.rodipit.quotes_api.data

import retrofit2.http.GET
import retrofit2.http.Query
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

}