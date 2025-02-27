package com.example.sign_in.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sign_in.ui.SignInScreenUi
import com.example.sign_in.viewmodel.SignInScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreenWrapper(
    modifier: Modifier = Modifier,
) {

    val viewModel: SignInScreenViewModel = koinViewModel()

    SignInScreenUi(
        modifier = modifier,
    )
}