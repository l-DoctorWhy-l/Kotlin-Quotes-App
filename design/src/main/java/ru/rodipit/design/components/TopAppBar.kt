package ru.rodipit.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.theme.AppTheme


@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    trailingButton: (@Composable () -> Unit)? = null,
    leadingButton: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingButton?.let {
            leadingButton.invoke()
        }
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineLarge
        )
        trailingButton?.let {
            Spacer(modifier = Modifier.weight(1f))
            trailingButton.invoke()
        }
    }
}


@Preview
@Composable
private fun TopAppBarPreviewWithLeadingAndTrailingButtons() {
    AppTheme {
        TopAppBar(
            title = "Title",
            trailingButton = {
                Button(
                    onClick = { },
                ) {
                    Text("Button 2")
                }
            },
            leadingButton = {
                Button(
                    onClick = { },
                ) {
                    Text("Button 1")
                }
            },
        )
    }
}

@Preview
@Composable
private fun TopAppBarPreviewWithLeadingButton() {
    AppTheme {
        TopAppBar(
            title = "Title",
            leadingButton = {
                Button(
                    onClick = { },
                ) {
                    Text("Button 1")
                }
            },
        )
    }
}

@Preview
@Composable
private fun TopAppBarPreviewWithTrailingButton() {
    AppTheme {
        TopAppBar(
            title = "Title",
            trailingButton = {
                Button(
                    onClick = { },
                ) {
                    Text("Button 1")
                }
            },
        )
    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    AppTheme {
        TopAppBar(
            title = "Title",
        )
    }
}

