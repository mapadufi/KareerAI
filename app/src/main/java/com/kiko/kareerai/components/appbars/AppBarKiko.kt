package com.kiko.kareerai.components.appbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.kiko.kareerai.ui.theme.KareerAITheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarKiko(
    title: String = "Kareer AI",
    onBack: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            titleContentColor = MaterialTheme.colorScheme.tertiaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.tertiaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onTertiary
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }
            } else if (onMenuClick != null) {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        }
    )
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "AppBar Light")
@Composable
fun AppBarKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        AppBarKiko(onMenuClick = {})
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "AppBar Dark")
@Composable
fun AppBarKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        AppBarKiko(onMenuClick = {})
    }
}
