package ru.rodipit.quotes_api.data

import retrofit2.http.GET
import ru.rodipit.quotes_api.data.dto.QuoteDto

internal interface QuotesApi {

    @GET("quotes")
    suspend fun loadQuotes(): List<QuoteDto>

    @GET("image")
    suspend fun loadImageSrc(): String

}