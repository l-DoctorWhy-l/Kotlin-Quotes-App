package com.example.image.api

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.image.ui.ImageScreenContent
import com.example.image.ui.ImageScreenPresenter
import com.example.image.viewmodel.ImageScreenViewModel
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.design.components.TopAppBar

@Composable
fun ImageScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: ImageScreenViewModel = koinViewModel()
    val presenter = remember { ImageScreenPresenter.Impl(viewModel = viewModel) }

    Column(
        modifier = modifier,
    ) {
        TopAppBar(title = "Image")
        ImageScreenContent(
            presenter = presenter,
        )
    }
}