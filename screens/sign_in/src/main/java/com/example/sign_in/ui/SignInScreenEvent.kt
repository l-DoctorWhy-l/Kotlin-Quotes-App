package com.example.sign_in.ui

interface SignInScreenEvent {

    data object BackToSignUp: SignInScreenEvent
    data object SignIn: SignInScreenEvent
    data class LoginChanged(val newLogin: String): SignInScreenEvent
    data class PasswordChanged(val newPassword: String): SignInScreenEvent
}