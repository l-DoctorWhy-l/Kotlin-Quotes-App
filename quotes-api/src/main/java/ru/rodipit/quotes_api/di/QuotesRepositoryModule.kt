package ru.rodipit.quotes_api.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rodipit.quotes_api.api.QuotesRepository
import ru.rodipit.quotes_api.data.QuotesApi
import ru.rodipit.quotes_api.data.QuotesRepositoryImpl

val quotesRepositoryModule = module {

    single<QuotesApi> {
        Retrofit.Builder()
            .baseUrl("http://192.168.3.21:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }

    single<QuotesRepository> {
        QuotesRepositoryImpl(
            api = get()
        )
    }

}