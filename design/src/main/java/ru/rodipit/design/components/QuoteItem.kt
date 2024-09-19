package ru.rodipit.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(1f, false)
        ) {
            Text(
                overflow = TextOverflow.Ellipsis,
                maxLines = 4,
                text = uiData.content,
            )
            Spacer(Modifier.height(4.dp))
            if (!uiData.author.isNullOrBlank()) {
                Text(
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = TextDecoration.Underline,
                    maxLines = 1,
                    text = uiData.author,
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
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            } else {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.baseline_favorite_border_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }

        }
    }
}

@Preview
@Composable
private fun QuoteItemPreview() {
    AppTheme {
        QuoteItem(uiData = QuoteItemUiData.forPreview())
    }
}