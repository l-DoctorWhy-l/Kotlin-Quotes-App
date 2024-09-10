package com.example.image.ui

import com.example.image.viewmodel.ImageScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal interface ImageScreenPresenter {

    val state: StateFlow<String?>

    class Impl(
        viewModel: ImageScreenViewModel,
    ): ImageScreenPresenter {

        override val state = viewModel.state

    }

    class Preview: ImageScreenPresenter {
        override val state = MutableStateFlow("")

    }


}