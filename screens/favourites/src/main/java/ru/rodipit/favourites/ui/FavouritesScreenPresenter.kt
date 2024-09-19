package ru.rodipit.favourites.ui

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.favourites.viewmodel.FavouritesScreenViewModel

internal interface FavouritesScreenPresenter {

    val state: StateFlow<UiState>

    fun unlikeQuote(pos: Int)


    class Impl(
        private val viewModel: FavouritesScreenViewModel,
    ): FavouritesScreenPresenter {

        override val state = viewModel.state
        override fun unlikeQuote(pos: Int) {
            viewModel.unlikeQuote(pos)
        }

    }

    class Preview(
        private val uiState: UiState = UiState.Loading,
    ): FavouritesScreenPresenter {
        override val state = MutableStateFlow(uiState)
        override fun unlikeQuote(pos: Int) = Unit

    }


}

@Immutable
internal sealed interface UiState {

    data object Loading: UiState

    data class Success(val quotes: List<QuoteItemUiData>): UiState {
        companion object {
            fun forPreview(): Success {
                return Success(
                    listOf(
                        QuoteItemUiData.forPreview(),
                        QuoteItemUiData.forPreview(),
                        QuoteItemUiData.forPreview(),
                    )
                )
            }
        }
    }

}