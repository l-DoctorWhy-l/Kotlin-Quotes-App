package ru.rodipit.quotes_api.api

import ru.rodipit.models.QuoteModel


interface QuotesRepository {

    suspend fun search(query: String): ConvertedResult<List<QuoteModel>, MyError>

    suspend fun quote(id: String): ConvertedResult<QuoteModel, MyError>

    suspend fun feed(): ConvertedResult<List<QuoteModel>, MyError>

    suspend fun login( login: String, password: String): ConvertedResult<String, MyError>

    suspend fun register(login: String, password: String): ConvertedResult<String, MyError>

}

sealed interface ConvertedResult<out D, out E: MyError> {
    data class Success<out D, out E : MyError>(val data: D): ConvertedResult<D, E>
    data class Error<out D, out E : MyError>(val error: E): ConvertedResult<D, E>
}

sealed interface MyError {
    data object NetworkError: MyError
    data object ServerError: MyError
    data object UnknownError: MyError
    data object InternetError: MyError
    data object NotFoundError: MyError
}