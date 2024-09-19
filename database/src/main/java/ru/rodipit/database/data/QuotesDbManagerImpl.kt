package ru.rodipit.database.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.rodipit.database.api.QuotesDbManager
import ru.rodipit.database.data.db.LikedQuotesDb
import ru.rodipit.database.data.dbo.QuoteEntity
import ru.rodipit.database.data.dbo.toQuote
import ru.rodipit.models.QuoteModel

internal class QuotesDbManagerImpl(
    private val roomDatabase: LikedQuotesDb
) : QuotesDbManager {
    override fun getAll() = roomDatabase.quotesDao().getAll().distinctUntilChanged()
            .map { quotes -> quotes.map { it.toQuote() } }


    override suspend fun insertQuote(quoteModel: QuoteModel) = withContext(Dispatchers.IO) {
        roomDatabase.quotesDao().insertQuote(
            quoteEntity = QuoteEntity(
                id = null,
                content = quoteModel.content,
                author = quoteModel.author,
            )
        )
    }

    override suspend fun deleteQuote(quoteModel: QuoteModel) = withContext(Dispatchers.IO) {
        roomDatabase.quotesDao().deleteQuote(
            content = quoteModel.content,
            author = quoteModel.author,
        )
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        roomDatabase.quotesDao().deleteAll()
    }

    override suspend fun isLikedState(quoteModel: QuoteModel) = withContext(Dispatchers.IO) {
        return@withContext roomDatabase.quotesDao().isLikedState(
            content = quoteModel.content,
            author = quoteModel.author,
        ) != 0
    }
}