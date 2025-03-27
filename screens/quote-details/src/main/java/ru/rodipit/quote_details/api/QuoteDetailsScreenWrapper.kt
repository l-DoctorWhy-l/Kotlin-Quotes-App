package ru.rodipit.quote_details.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.rodipit.quote_details.ui.QuoteDetailsScreenPresenter
import ru.rodipit.quote_details.ui.QuoteDetailsScreenUi
import ru.rodipit.quote_details.viewmodel.QuoteDetailsScreenViewModel
import ru.rodipit.quote_details.viewmodel.QuoteDetailsScreenViewModelFactory

@Composable
fun QuoteDetailsScreenWrapper(
    id: String,
    modifier: Modifier = Modifier,
) {
    val viewModel: QuoteDetailsScreenViewModel = viewModel(
        factory = QuoteDetailsScreenViewModelFactory(
            quoteId = id,
        )
    )
    val presenter = remember { QuoteDetailsScreenPresenter.Impl(viewModel = viewModel) }
    QuoteDetailsScreenUi(
        presenter = presenter,
        modifier = modifier,
    )
}
