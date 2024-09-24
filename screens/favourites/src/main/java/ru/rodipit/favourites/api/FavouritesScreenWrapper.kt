package ru.rodipit.favourites.api

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.favourites.ui.FavouritesScreenContent
import ru.rodipit.favourites.ui.FavouritesScreenPresenter
import ru.rodipit.favourites.viewmodel.FavouritesScreenViewModel

@Composable
fun FavouritesScreenWrapper(
    modifier: Modifier = Modifier,
) {

    val viewModel: FavouritesScreenViewModel = koinViewModel()

    val context = LocalContext.current

    Column(modifier = modifier) {
        TopAppBar(
            title = "Favourites",
        )
        FavouritesScreenContent(
            presenter = remember(viewModel) { FavouritesScreenPresenter.Impl(viewModel = viewModel, context = context) }
        )
    }
}