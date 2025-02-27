package com.example.splash.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.splash.ui.SplashScreenUi
import com.example.splash.viewmodel.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: SplashScreenViewModel = koinViewModel()

    SplashScreenUi(
        onLoad = viewModel::loadAccount,
        modifier = modifier,
    )
}
