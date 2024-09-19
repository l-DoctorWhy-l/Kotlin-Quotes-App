package ru.rodipit.database.api

import kotlinx.coroutines.flow.Flow
import ru.rodipit.models.QuoteModel

interface QuotesDbManager {


    fun getAll(): Flow<List<QuoteModel>>

    suspend fun insertQuote(quoteModel: QuoteModel)

    suspend fun deleteQuote(quoteModel: QuoteModel)

    suspend fun deleteAll()

    suspend fun isLikedState(quoteModel: QuoteModel): Boolean

}