package com.example.sign_in.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.Route
import ru.rodipit.design.R
import com.example.sign_in.ui.SignInScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rodipit.quotes_api.api.ConvertedResult
import ru.rodipit.quotes_api.api.QuotesRepository
import ru.rodipit.utils.StringResourceProvider

internal class SignInScreenViewModel(
    private val navigator: AppNavigator,
    private val repository: QuotesRepository,
    private val stringProvider: StringResourceProvider,
): ViewModel() {


    private val _login = MutableStateFlow("")
    val login = _login.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _errorMessages = MutableStateFlow("")
    val errorMessages = _errorMessages.asStateFlow()


    fun onEvent(event: SignInScreenEvent) {
        when (event) {
            is SignInScreenEvent.BackToSignUp -> {
                navigateToSignUp()
            }
            is SignInScreenEvent.SignIn -> {
                signIn()
            }
            is SignInScreenEvent.LoginChanged -> changeLogin(event.newLogin)
            is SignInScreenEvent.PasswordChanged -> changePassword(event.newPassword)
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            val result = repository.login(
                login = login.value,
                password = password.value,
            )

            when (result) {
                is ConvertedResult.Success -> {
                    navigator.navigateTo(Route.Main)
                }
                is ConvertedResult.Error -> {
                    _errorMessages.emit(stringProvider.getString(R.string.something_error_message))
                }
            }
        }
    }


    private fun changeLogin(newLogin: String) {
        _login.update { newLogin }
    }

    private fun changePassword(newPassword: String) {
        _password.update { newPassword }
    }


    private fun navigateToSignUp() {
        navigator.navigateTo(Route.SignUp)
    }

}