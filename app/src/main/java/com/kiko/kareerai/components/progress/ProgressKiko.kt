package com.kiko.kareerai.components.progress

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.ui.theme.KareerAITheme


@Composable
fun ProgressKiko(
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.tertiary,
    trackColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.secondaryContainer
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor
    )
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Progress Light")
@Composable
fun ProgressKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        ProgressKiko(modifier = Modifier.size(64.dp))
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Progress Dark")
@Composable
fun ProgressKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        ProgressKiko(modifier = Modifier.size(64.dp))
    }
}
