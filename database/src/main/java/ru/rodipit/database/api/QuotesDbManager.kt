package ru.rodipit.database.api

import kotlinx.coroutines.flow.Flow
import ru.rodipit.models.QuoteModel

interface QuotesDbManager {


    suspend fun getAll(): Flow<List<QuoteModel>>

    suspend fun insertQuote(quoteModel: QuoteModel)

    suspend fun deleteAll()

}