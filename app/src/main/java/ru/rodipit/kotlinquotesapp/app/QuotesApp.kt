package ru.rodipit.kotlinquotesapp.app

import android.app.Application
import com.example.navigation.di.navigationModule
import com.example.search.di.searchScreenModule
import com.example.sign_in.di.signInScreenModule
import com.example.sign_up.di.signUpScreenModule
import com.example.splash.di.splashScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.rodipit.add_quote.di.addQuoteScreenModule
import ru.rodipit.database.di.databaseModule
import ru.rodipit.favourites.di.favouritesScreenModule
import ru.rodipit.main_screen.di.mainScreenModule
import ru.rodipit.profile.di.profileScreenModule
import ru.rodipit.quote_details.di.quoteDetailsScreenModule
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
                searchScreenModule,
                navigationModule,
                splashScreenModule,
                signInScreenModule,
                signUpScreenModule,
                profileScreenModule,
                addQuoteScreenModule,
                quoteDetailsScreenModule,
            )
        }
    }
}