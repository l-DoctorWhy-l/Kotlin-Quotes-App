package ru.rodipit.main_screen.ui

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.main_screen.viewmodel.MainScreenViewModel

internal interface MainScreenPresenter {

    val state: StateFlow<UiState>
    val refreshingState: StateFlow<Boolean>

    fun likeQuote(pos: Int)

    fun refresh()

    class Impl(
        private val viewModel: MainScreenViewModel,
    ): MainScreenPresenter {

        override val state = viewModel.state
        override val refreshingState = viewModel.isRefreshingState

        override fun likeQuote(pos: Int) {
            viewModel.likeQuote(pos)
        }

        override fun refresh() {
            viewModel.loadQuotes()
        }

    }

    class Preview(
        private val uiState: UiState = UiState.Loading,
    ): MainScreenPresenter {
        override val state = MutableStateFlow(uiState)
        override val refreshingState = MutableStateFlow(false)

        override fun likeQuote(pos: Int) = Unit
        override fun refresh() = Unit

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