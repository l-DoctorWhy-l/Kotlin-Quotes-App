package ru.rodipit.database.di

import androidx.room.Room
import org.koin.dsl.module
import ru.rodipit.database.api.QuotesDbManager
import ru.rodipit.database.data.QuotesDbManagerImpl
import ru.rodipit.database.data.db.LikedQuotesDb

val databaseModule = module {

    single<LikedQuotesDb> {
        Room.databaseBuilder(
            context = get(),
            klass = LikedQuotesDb::class.java,
            name = "liked_quotes",
        ).build()
    }

    single<QuotesDbManager> {
        QuotesDbManagerImpl(
            roomDatabase = get()
        )
    }

}