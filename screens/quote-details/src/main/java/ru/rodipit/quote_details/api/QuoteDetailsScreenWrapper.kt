package ru.rodipit.quote_details.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.quote_details.ui.QuoteDetailsScreenPresenter
import ru.rodipit.quote_details.ui.QuoteDetailsScreenUi
import ru.rodipit.quote_details.viewmodel.QuoteDetailsScreenViewModel

@Composable
fun QuoteDetailsScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: QuoteDetailsScreenViewModel = koinViewModel()
    val presenter = remember { QuoteDetailsScreenPresenter.Impl(viewModel = viewModel) }
    QuoteDetailsScreenUi(
        presenter = presenter,
        modifier = modifier,
    )
}
