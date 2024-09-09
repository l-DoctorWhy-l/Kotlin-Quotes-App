package ru.rodipit.main_screen.api

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.main_screen.ui.MainScreenContent
import ru.rodipit.main_screen.ui.MainScreenPresenter
import ru.rodipit.main_screen.viewmodel.MainScreenViewModel

@Composable
fun MainScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: MainScreenViewModel = koinViewModel()
    val presenter = remember { MainScreenPresenter.Impl(viewModel = viewModel) }

    Column(
        modifier = modifier,
    ) {
        TopAppBar(title = "Home")
        MainScreenContent(
            presenter = presenter,
        )
    }
}
