package ru.rodipit.database.data.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.rodipit.models.QuoteModel

@Entity(tableName = "liked_quotes")
internal data class QuoteEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("content") val content: String,
    @ColumnInfo("author") val author: String?,
)

internal fun QuoteEntity.toQuote(): QuoteModel {
    return QuoteModel(
        id = this.id,
        content = this.content,
        film = this.author,
    )
}


