package ru.rodipit.add_quote.api

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.add_quote.ui.AddQuoteScreenPresenter
import ru.rodipit.add_quote.ui.AddQuoteScreenUi
import ru.rodipit.add_quote.viewmodel.AddQuoteScreenViewModel

@Composable
fun AddQuoteScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: AddQuoteScreenViewModel = koinViewModel()
    val presenter = remember { AddQuoteScreenPresenter.Impl(viewModel = viewModel) }

    Column(
        modifier = modifier,
    ) {
        AddQuoteScreenUi(
            presenter = presenter,
        )
    }
}