package ru.rodipit.settings.ui

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.design.R
import ru.rodipit.design.components.BackButton
import ru.rodipit.utils.thenIf

@Composable
internal fun SettingsScreenUi(
    presenter: SettingsScreenPresenter,
    modifier: Modifier = Modifier,
) {

    val uiData by presenter.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TopAppBar(
            title = stringResource(R.string.settings_screen_top_bar_title),
            leadingButton = {
                BackButton(
                    onBackButtonClicked = presenter::onBackButtonClick,
                )
            },
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(180.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ThemeTypeItem(
                modifier = Modifier.weight(1f),
                iconId = R.drawable.icon_sun,
                stringId = R.string.light_theme_type_text,
                isSelected = uiData.theme == ThemeType.Day,
                themeType = ThemeType.Day,
                onThemeChanged = presenter::setTheme,
            )
            ThemeTypeItem(
                modifier = Modifier.weight(1f),
                iconId = R.drawable.icon_moon,
                stringId = R.string.dark_theme_type_text,
                isSelected = uiData.theme == ThemeType.Night,
                themeType = ThemeType.Night,
                onThemeChanged = presenter::setTheme,
            )
            ThemeTypeItem(
                modifier = Modifier.weight(1f),
                iconId = R.drawable.icon_day_and_night,
                stringId = R.string.auto_theme_type_text,
                isSelected = uiData.theme == ThemeType.Auto,
                themeType = ThemeType.Auto,
                onThemeChanged = presenter::setTheme,
            )
        }
    }
}


@Composable
private fun ThemeTypeItem(
    @DrawableRes iconId: Int,
    @StringRes stringId: Int,
    isSelected: Boolean,
    themeType: ThemeType,
    onThemeChanged: (ThemeType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(14.dp),
            )
            .thenIf(
                Modifier.border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(14.dp)
                )
            ) { isSelected }
            .clickable {
                onThemeChanged.invoke(themeType)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(iconId),
            contentDescription = null,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(stringId),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
private fun SettingsScreenUiPreview() {
    AppTheme {
        SettingsScreenUi(
            presenter = SettingsScreenPresenter.Preview(
                SettingsScreenUiState(theme = ThemeType.Auto)
            )
        )
    }
}