package ru.rodipit.design.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.rodipit.design.R


@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
) {
    IconButton(
        onClick = onBackButtonClicked,
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_24),
            contentDescription = null,
        )
    }
}