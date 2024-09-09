package ru.rodipit.database.data.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.rodipit.models.QuoteModel

@Entity(tableName = "liked_quotes")
internal data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo("content") val content: String,
    @ColumnInfo("author") val author: String?,
)

internal fun QuoteEntity.toQuote(): QuoteModel {
    return QuoteModel(
        content = content,
        author = author,
    )
}


