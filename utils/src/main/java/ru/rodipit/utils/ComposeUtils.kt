package ru.rodipit.utils

import androidx.compose.ui.Modifier


fun Modifier.thenIf(
    other: Modifier,
    expression: () -> Boolean,
): Modifier {
    return if (expression.invoke()) {
        this.then(other)
    } else this

}