package ru.rodipit.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.R
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.design.shimmers.SingleLineTextShimmer
import ru.rodipit.design.theme.AppTheme

@Composable
fun QuoteItem(
    uiData: QuoteItemUiData,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .clickable { onClick?.invoke() }
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(1f, false)
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3,
                text = uiData.content,
            )
            Spacer(Modifier.height(4.dp))
            if (!uiData.film.isNullOrBlank()) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                    textDecoration = TextDecoration.Underline,
                    maxLines = 1,
                    text = uiData.film,
                )
            }
        }

        IconButton(
            onClick = { onClick?.invoke() }
        ) {

            if (uiData.isLiked) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.baseline_favorite_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            } else {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.baseline_favorite_border_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}

@Composable
fun ExpandedQuoteItem(
    uiData: QuoteItemUiData,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                text = uiData.content,
            )
            Spacer(Modifier.height(4.dp))
            if (!uiData.film.isNullOrBlank()) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                    textDecoration = TextDecoration.Underline,
                    maxLines = 1,
                    text = uiData.film,
                )
            }
        }

        IconButton(
            onClick = { onClick?.invoke() }
        ) {

            if (uiData.isLiked) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.baseline_favorite_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            } else {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.baseline_favorite_border_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}

@Composable
fun QuoteItemShimmer(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {

            SingleLineTextShimmer(
                width = 160.dp,
                textStyle = MaterialTheme.typography.bodyLarge,
                isLoading = isLoading,
            )
            SingleLineTextShimmer(
                width = 100.dp,
                textStyle = MaterialTheme.typography.bodyLarge,
                isLoading = isLoading,
            )
            Spacer(Modifier.height(4.dp))
            SingleLineTextShimmer(
                width = 80.dp,
                textStyle = MaterialTheme.typography.bodyLarge,
                isLoading = isLoading,
            )
        }
    }
}

@Preview
@Composable
private fun QuoteItemPreview() {
    AppTheme {
        Column {
            QuoteItem(uiData = QuoteItemUiData.forPreview())
            Spacer(modifier = Modifier.height(16.dp))
            QuoteItemShimmer(isLoading = true)
        }
    }
}
@Preview
@Composable
private fun ExpandedQuoteItemPreview() {
    AppTheme {
        Column {
            ExpandedQuoteItem(uiData = QuoteItemUiData(
                id = "1",
                film = "asdadasdadadadadadadadadadadada",
                isLiked = true,
                content = "adasoidaosidjasiojdoadjasjdajdojdaosjdoajjdajdoajoajdoaijdaodiadoaidaodaodajoajaoaijdaoidaoidjaoijdajdaosidoajdoajdoajdoajodasjdoajdadaodjaoidjasodada"
            ))
        }
    }
}