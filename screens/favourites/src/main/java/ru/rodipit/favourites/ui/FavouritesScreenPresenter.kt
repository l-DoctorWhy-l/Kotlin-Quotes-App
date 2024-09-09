package ru.rodipit.favourites.ui

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.favourites.viewmodel.FavouritesScreenViewModel

internal interface FavouritesScreenPresenter {

    val state: StateFlow<UiState>

    class Impl(
        viewModel: FavouritesScreenViewModel,
    ): FavouritesScreenPresenter {

        override val state = viewModel.state

    }

    class Preview(
        private val uiState: UiState = UiState.Loading,
    ): FavouritesScreenPresenter {
        override val state = MutableStateFlow(uiState)

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