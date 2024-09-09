package ru.rodipit.kotlinquotesapp.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.rodipit.database.di.databaseModule
import ru.rodipit.favourites.di.favouritesScreenModule
import ru.rodipit.main_screen.di.mainScreenModule
import ru.rodipit.quotes_api.di.quotesRepositoryModule

class QuotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuotesApp)
            modules(
                mainScreenModule,
                favouritesScreenModule,
                quotesRepositoryModule,
                databaseModule,
            )
        }
    }
}