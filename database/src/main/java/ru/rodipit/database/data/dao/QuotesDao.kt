package ru.rodipit.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.rodipit.database.data.dbo.QuoteEntity

@Dao
internal interface QuotesDao {

    @Insert
    fun insertQuote(quoteEntity: QuoteEntity)

    @Query("SELECT * FROM liked_quotes")
    fun getAll(): Flow<List<QuoteEntity>>

    @Query("DELETE FROM liked_quotes")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM liked_quotes WHERE content = :content AND author = :author >= 1")
    fun isLikedState(content: String, author: String?): Int

    @Query("DELETE FROM liked_quotes WHERE content = :content AND author = :author")
    fun deleteQuote(content: String, author: String?)


}