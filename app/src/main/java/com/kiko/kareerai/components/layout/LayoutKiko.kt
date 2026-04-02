package com.kiko.kareerai.components.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.kiko.kareerai.components.appbars.AppBarKiko
import com.kiko.kareerai.components.appbars.BottomBarKiko
import com.kiko.kareerai.ui.theme.AppThemeType
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.UsuarioViewModel
import kotlinx.coroutines.launch
import android.app.Activity

@Composable
fun LayoutKiko(
    navController: NavController? = null,
    usuarioViewModel: UsuarioViewModel? = null,
    title: String = "Kiko Componentes",
    drawerItems: List<KikoDrawerItem> = emptyList(),
    onBack: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null,
    componentCode: String = "",
    isDarkTheme: Boolean = false,
    onDarkModeChange: (Boolean) -> Unit = {},
    selectedTheme: AppThemeType = AppThemeType.PADRAO,
    onThemeTypeChange: (AppThemeType) -> Unit = {},
    topBar: @Composable (() -> Unit) -> Unit = { onOpenDrawer ->
        AppBarKiko(
            title = title,
            onBack = onBack,
            onMenuClick = if (onMenuClick != null) onMenuClick else if (drawerItems.isNotEmpty()) onOpenDrawer else null
        )
    },
    bottomBar: @Composable () -> Unit = {
        BottomBarKiko()
    },
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    NavigationDrawerKiko(
        drawerState = drawerState,
        items = drawerItems,
        onItemClick = { route -> navController?.navigate(route) },
        onLogoutClick = {
            (context as? Activity)?.finishAffinity()
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = { topBar { scope.launch { drawerState.open() } } },
            bottomBar = bottomBar,
            content = content
        )
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, showSystemUi = true, name = "LayoutBase Light")
@Composable
fun LayoutBaseKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        LayoutKiko(
            title = "Preview Light",
            drawerItems = emptyList(),
            componentCode = "fun Exemplo() { }",
            isDarkTheme = false,
            onDarkModeChange = {},
            selectedTheme = AppThemeType.PADRAO,
            onThemeTypeChange = {}
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text(text = "Conteúdo da Tela", color = MaterialTheme.colorScheme.tertiary)
            }
        }
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, showSystemUi = true, name = "LayoutBase Dark")
@Composable
fun LayoutBaseKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        LayoutKiko(
            title = "Preview Dark",
            drawerItems = emptyList(),
            componentCode = "fun Exemplo() { }",
            isDarkTheme = true,
            onDarkModeChange = {},
            selectedTheme = AppThemeType.PADRAO,
            onThemeTypeChange = {}
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text(text = "Conteúdo da Tela", color = MaterialTheme.colorScheme.tertiary)
            }
        }
    }
}
