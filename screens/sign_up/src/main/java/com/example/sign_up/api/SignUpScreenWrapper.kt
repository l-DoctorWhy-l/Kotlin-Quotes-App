package com.example.sign_up.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sign_up.ui.SignUpScreenUi
import com.example.sign_up.viewmodel.SignUpScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreenWrapper(
    modifier: Modifier = Modifier,
) {

    val viewModel: SignUpScreenViewModel = koinViewModel()

    SignUpScreenUi(
        onBackToSignIn = viewModel::backToSignIn,
        modifier = modifier,
    )
}