package com.kiko.kareerai.components.appbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.ui.theme.KareerAITheme


@Composable
fun BottomBarKiko() {
    BottomAppBar(
        modifier = Modifier.height(48.dp),
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.tertiaryContainer,
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "© 2026 Marcos Figueiredo",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "BottomBarBase Light")
@Composable
fun BottomBarBaseKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        BottomBarKiko()
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "BottomBarBase Dark")
@Composable
fun BottomBarBaseKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        BottomBarKiko()
    }
}
