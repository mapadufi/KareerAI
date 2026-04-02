package com.kiko.kareerai.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.ui.theme.KareerAITheme


@Composable
fun KikoFab(onClick: () -> Unit,
            modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiary
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Floating action button.")
    }
}

@Composable
fun KikoSmall(onClick: () -> Unit,
              modifier: Modifier = Modifier) {
    SmallFloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiary
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Small floating action button.")
    }
}

@Composable
fun KikoLarge(onClick: () -> Unit,
              modifier: Modifier = Modifier) {
    LargeFloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiary,
        shape = CircleShape,
        modifier = Modifier.size(60.dp, 60.dp) // exemplo de tamanho menor
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Large floating action button")
    }
}

@Composable
fun KikoExtended(onClick: () -> Unit,
                 modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiary,
        icon = { Icon(Icons.Filled.Edit, contentDescription = "Extended floating action button.") },
        text = { Text(text = "Extended FAB") }
    )
}

@Composable
private fun FabGroupPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KikoSmall(onClick = {})
            KikoFab(onClick = {})
            KikoLarge(onClick = {})
            KikoExtended(onClick = {})
        }
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Fab Light")
@Composable
fun FabKikoPreviewLight() {
    KareerAITheme(darkTheme = false) {
        FabGroupPreview()
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Fab Dark")
@Composable
fun FabKikoPreviewDark() {
    KareerAITheme(darkTheme = true) {
        FabGroupPreview()
    }
}
