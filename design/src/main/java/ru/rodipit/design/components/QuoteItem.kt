package ru.rodipit.design.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.design.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuoteItem(
    uiData: QuoteItemUiData,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .combinedClickable(
                onClick = { onClick?.invoke() },
                onLongClick = { onLongClick?.invoke() }
            )
            .padding(8.dp),
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

}

@Preview
@Composable
private fun QuoteItemPreview() {
    AppTheme {
        QuoteItem(uiData = QuoteItemUiData.forPreview())
    }
}