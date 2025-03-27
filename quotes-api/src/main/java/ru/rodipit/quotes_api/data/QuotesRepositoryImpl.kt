package ru.rodipit.quotes_api.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.rodipit.quotes_api.api.ConvertedResult
import ru.rodipit.quotes_api.api.MyError
import ru.rodipit.quotes_api.api.QuotesRepository
import ru.rodipit.quotes_api.data.dto.toQuote
import java.io.IOException

internal class QuotesRepositoryImpl(
    private val api: QuotesApi
): QuotesRepository {

    override suspend fun search(query: String) = withContext(Dispatchers.IO) {
        try {
            val result = api.search(
                query = query,
            )
            val quotes = result.mapNotNull { it.toQuote() }
            return@withContext ConvertedResult.Success(data = quotes)
        }  catch (e: IOException) {
            return@withContext ConvertedResult.Error(error = MyError.InternetError)
        } catch (e: HttpException) {
            return@withContext when {
                e.code() == 404 -> ConvertedResult.Error(error = MyError.NotFoundError)
                e.code() >= 500 -> ConvertedResult.Error(error = MyError.ServerError)
                e.code() >= 400 -> ConvertedResult.Error(error = MyError.NetworkError)
                else -> ConvertedResult.Error(error = MyError.UnknownError)
            }
        }
    }


    override suspend fun quote(id: String) = withContext(Dispatchers.IO) {
        try {
            val result = api.quote(
                id = id,
            )
            val quote = result.toQuote() ?: return@withContext ConvertedResult.Error(error = MyError.NotFoundError)
            return@withContext ConvertedResult.Success(data = quote)
        }  catch (e: IOException) {
            return@withContext ConvertedResult.Error(error = MyError.InternetError)
        } catch (e: HttpException) {
            return@withContext when {
                e.code() == 404 -> ConvertedResult.Error(error = MyError.NotFoundError)
                e.code() >= 500 -> ConvertedResult.Error(error = MyError.ServerError)
                e.code() >= 400 -> ConvertedResult.Error(error = MyError.NetworkError)
                else -> ConvertedResult.Error(error = MyError.UnknownError)
            }
        }
    }

}