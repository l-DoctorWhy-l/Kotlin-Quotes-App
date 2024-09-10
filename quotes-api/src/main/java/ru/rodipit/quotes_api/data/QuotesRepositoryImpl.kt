package ru.rodipit.quotes_api.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.rodipit.models.QuoteModel
import ru.rodipit.quotes_api.api.QuotesRepository
import ru.rodipit.quotes_api.data.dto.toQuote

internal class QuotesRepositoryImpl(
    private val api: QuotesApi
): QuotesRepository {

    override suspend fun loadQuotes(): List<QuoteModel> = withContext(Dispatchers.IO) {
        return@withContext api.loadQuotes().mapNotNull { it.toQuote() }
    }

    override suspend fun loadImageUrl(): String = withContext(Dispatchers.IO) {
        return@withContext api.loadImageSrc()
    }
}