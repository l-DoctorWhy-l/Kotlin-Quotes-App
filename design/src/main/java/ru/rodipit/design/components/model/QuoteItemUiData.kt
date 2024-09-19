package ru.rodipit.design.components.model

import androidx.compose.runtime.Stable

@Stable
data class QuoteItemUiData(
    val content: String,
    val author: String?,
    val isLiked: Boolean = false,
) {
    companion object {
        fun forPreview(): QuoteItemUiData {
            return QuoteItemUiData(
                content = "Don't be afraid that you do not know something. Be afraid of not learning about it.",
                author = "Zen Proverb",
            )
        }
    }
}