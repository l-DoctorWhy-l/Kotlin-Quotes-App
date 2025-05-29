package com.example.sign_up.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.R
import ru.rodipit.design.theme.AppTheme

@Composable
internal fun SignUpScreenUi(
    login: String,
    password: String,
    error: String,
    repeatingPassword: String,
    onEvent: (SignUpScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = stringResource(R.string.sign_up_screen_title),
            style = MaterialTheme.typography.displayMedium,
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = login,
            onValueChange = { onEvent(SignUpScreenEvent.LoginChanged(it)) },
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.login_text_field_label_text))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_person_24),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = null,
                )
            }
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { onEvent(SignUpScreenEvent.PasswordChanged(it)) },
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.password_text_field_label_text))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_lock_24),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = null,
                )
            }
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = repeatingPassword,
            onValueChange = { onEvent(SignUpScreenEvent.RepeatingPasswordChanged(it)) },
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.repeat_password_text_field_label_text))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_lock_24),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = null,
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick =  { onEvent(SignUpScreenEvent.SignUp) }
        ) {
            Text(text = stringResource(R.string.sign_up_button_text))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = stringResource(R.string.go_to_sign_in_trailing_text)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.go_to_sign_in_text),
                color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.clickable { onEvent(SignUpScreenEvent.BackToSignIn) }
            )
        }
        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}

@Preview
@Composable
private fun SignUpScreenUiPreview(

){
    AppTheme {
        SignUpScreenUi(
            login = "",
            password = "",
            repeatingPassword = "",
            error = "",
            onEvent = {}
        )
    }
}