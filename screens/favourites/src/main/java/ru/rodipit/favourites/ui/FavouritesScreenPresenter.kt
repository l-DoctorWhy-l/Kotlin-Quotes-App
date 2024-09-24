package ru.rodipit.favourites.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Immutable
import androidx.work.WorkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.favourites.viewmodel.FavouritesScreenViewModel

internal interface FavouritesScreenPresenter {

    val state: StateFlow<UiState>

    fun unlikeQuote(pos: Int)

    fun doWork()


    class Impl(
        private val viewModel: FavouritesScreenViewModel,
        private val context: Context,
    ): FavouritesScreenPresenter {

        override val state = viewModel.state
        override fun unlikeQuote(pos: Int) {
            viewModel.unlikeQuote(pos)
        }

        override fun doWork() {
            Toast.makeText(context, "Work will be start in 10 seconds!", Toast.LENGTH_LONG).show()
            viewModel.doWork(WorkManager.getInstance(context))
        }

    }

    class Preview(
        private val uiState: UiState = UiState.Loading,
    ): FavouritesScreenPresenter {
        override val state = MutableStateFlow(uiState)
        override fun unlikeQuote(pos: Int) = Unit
        override fun doWork() = Unit

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