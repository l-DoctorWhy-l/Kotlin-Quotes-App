package ru.rodipit.quotes_api.data.dto

import com.google.gson.annotations.SerializedName
import ru.rodipit.models.QuoteModel

data class QuoteDto(
    @SerializedName("id") val id: String,
    @SerializedName("film") val film: String?,
    @SerializedName("desc") val content: String?,
)

internal fun QuoteDto.toQuote(): QuoteModel? {
    return QuoteModel(
        id = id,
        content = content ?: return null ,
        film = film,
    )
}
