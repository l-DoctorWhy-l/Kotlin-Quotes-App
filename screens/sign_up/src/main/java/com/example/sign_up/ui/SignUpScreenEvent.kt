package com.example.sign_up.ui

interface SignUpScreenEvent {

    data object BackToSignIn: SignUpScreenEvent
    data object SignUp: SignUpScreenEvent
    data class LoginChanged(val newLogin: String): SignUpScreenEvent
    data class PasswordChanged(val newPassword: String): SignUpScreenEvent
    data class RepeatingPasswordChanged(val newRepeatingPassword: String): SignUpScreenEvent
}