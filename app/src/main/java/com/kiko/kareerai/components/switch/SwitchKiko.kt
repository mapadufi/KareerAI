package com.kiko.kareerai.components.switch

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kiko.kareerai.ui.theme.KareerAITheme

@Composable
fun KikoSwitchWithIconInsideThumb(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    thumbColorChecked: Color = MaterialTheme.colorScheme.surface,
    thumbColorUnchecked: Color = MaterialTheme.colorScheme.primary,
    trackColorChecked: Color = MaterialTheme.colorScheme.onPrimary,
    trackColorUnchecked: Color = MaterialTheme.colorScheme.onPrimary
) {
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = thumbColorChecked,
            checkedTrackColor = trackColorChecked,
            uncheckedThumbColor = thumbColorUnchecked,
            uncheckedTrackColor = trackColorUnchecked,
            uncheckedBorderColor = MaterialTheme.colorScheme.primary, // borda quando desligado
            checkedBorderColor = MaterialTheme.colorScheme.tertiary     // borda quando ligado
        ),
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Brightness2,
                    contentDescription = "Lua",
                    tint = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        } else {
            {
                Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = "Sol",
                    tint = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        }
    )

}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Switch Light")
@Composable
fun KikoSwitchPreviewLight() {
    var checked by remember { mutableStateOf(false) }
    KareerAITheme (darkTheme = false) {
        KikoSwitchWithIconInsideThumb(
            checked = checked,
            onCheckedChange = { checked = it }
        )
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Switch Dark")
@Composable
fun KikoSwitchPreviewDark() {
    var checked by remember { mutableStateOf(true) }
    KareerAITheme (darkTheme = true) {
        KikoSwitchWithIconInsideThumb(
            checked = checked,
            onCheckedChange = { checked = it }
        )
    }
}
