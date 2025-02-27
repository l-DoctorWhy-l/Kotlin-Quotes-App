package ru.rodipit.favourites.api

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.favourites.ui.FavouritesScreenUi
import ru.rodipit.favourites.ui.FavouritesScreenPresenter
import ru.rodipit.favourites.viewmodel.FavouritesScreenViewModel

@Composable
fun FavouritesScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: FavouritesScreenViewModel = koinViewModel()

    Column(modifier = modifier) {
        FavouritesScreenUi(
            presenter = remember(viewModel) { FavouritesScreenPresenter.Impl(viewModel = viewModel) }
        )
    }
}