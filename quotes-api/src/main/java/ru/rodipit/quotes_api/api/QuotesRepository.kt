package ru.rodipit.quotes_api.api

import ru.rodipit.models.QuoteModel

interface QuotesRepository {

    suspend fun loadQuotes(): List<QuoteModel>

}