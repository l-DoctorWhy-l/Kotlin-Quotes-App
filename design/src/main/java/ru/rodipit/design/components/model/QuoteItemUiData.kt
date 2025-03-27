package ru.rodipit.design.components.model

import androidx.compose.runtime.Stable

@Stable
data class QuoteItemUiData(
    val id: String,
    val content: String,
    val film: String?,
    val isLiked: Boolean = false,
) {
    companion object {
        fun forPreview(): QuoteItemUiData {
            return QuoteItemUiData(
                id = "1",
                content = "Don't be afraid that you do not know something. Be afraid of not learning about it.",
                film = "Zen Proverb",
            )
        }
    }
}