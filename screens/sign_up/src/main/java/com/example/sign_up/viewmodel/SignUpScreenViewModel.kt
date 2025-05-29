package com.example.sign_up.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.Route
import com.example.sign_up.ui.SignUpScreenEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rodipit.design.R
import ru.rodipit.quotes_api.api.ConvertedResult
import ru.rodipit.quotes_api.api.QuotesRepository
import ru.rodipit.utils.StringResourceProvider

internal class SignUpScreenViewModel(
    private val appNavigator: AppNavigator,
    private val repository: QuotesRepository,
    private val stringProvider: StringResourceProvider,
    ): ViewModel() {


    private val _login = MutableStateFlow("")
    val login = _login.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _repeatingPassword = MutableStateFlow("")
    val repeatingPassword = _repeatingPassword.asStateFlow()


    private val _errorMessages = MutableStateFlow("")
    val errorMessages = _errorMessages.asStateFlow()


    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.BackToSignIn -> {
                backToSignIn()
            }
            is SignUpScreenEvent.SignUp -> {
                signUp()
            }
            is SignUpScreenEvent.LoginChanged -> changeLogin(event.newLogin)
            is SignUpScreenEvent.PasswordChanged -> changePassword(event.newPassword)
            is SignUpScreenEvent.RepeatingPasswordChanged -> changeRepeatingPassword(event.newRepeatingPassword)
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            if (password.value != repeatingPassword.value) {
                _errorMessages.emit(stringProvider.getString(R.string.not_equal_passwords_error_message))
                return@launch
            }
            val result = repository.register(
                login = login.value,
                password = password.value,
            )

            when (result) {
                is ConvertedResult.Success -> {
                    appNavigator.navigateTo(Route.Main)
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

    private fun changeRepeatingPassword(newRepeatingPassword: String) {
        _repeatingPassword.update { newRepeatingPassword }
    }

    private fun backToSignIn() {
        appNavigator.popBackStack()
    }

}