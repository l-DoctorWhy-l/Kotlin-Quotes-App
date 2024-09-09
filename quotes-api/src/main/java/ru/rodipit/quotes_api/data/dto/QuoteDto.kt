package ru.rodipit.quotes_api.data.dto

import com.google.gson.annotations.SerializedName
import ru.rodipit.models.QuoteModel

data class QuoteDto(
    @SerializedName("q") val content: String?,
    @SerializedName("a") val author: String?
)

internal fun QuoteDto.toQuote(): QuoteModel? {
    return QuoteModel(
        content = content ?: return null ,
        author = author,
    )
}
