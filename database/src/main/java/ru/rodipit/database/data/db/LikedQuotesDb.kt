package ru.rodipit.database.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rodipit.database.data.dao.QuotesDao
import ru.rodipit.database.data.dbo.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
internal abstract class LikedQuotesDb: RoomDatabase() {
    abstract fun quotesDao(): QuotesDao
}