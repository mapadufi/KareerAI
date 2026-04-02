package com.kiko.kareerai.components.layout

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kiko.kareerai.components.appbars.AppBarKiko
import com.kiko.kareerai.components.appbars.BottomBarKiko
import com.kiko.kareerai.ui.theme.AppThemeType
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.UsuarioViewModel
import kotlinx.coroutines.launch

@Composable
fun LayoutKiko(
    navController: NavController? = null,
    usuarioViewModel: UsuarioViewModel? = null,
    title: String = "Kareer AI",
    onBack: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null,
    isDarkTheme: Boolean = false,
    selectedTheme: AppThemeType = AppThemeType.PADRAO,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // LISTA ÚNICA E IMUTÁVEL PARA TODAS AS TELAS
    val officialDrawerItems = getDrawerItems()

    val currentRoute = navController
        ?.currentBackStackEntryAsState()
        ?.value
        ?.destination
        ?.route ?: ""

    NavigationDrawerKiko(
        selectedRoute = currentRoute,
        drawerState = drawerState,
        items = officialDrawerItems,
        onItemClick = { route ->
            navController?.navigate(route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        onLogoutClick = { (context as? Activity)?.finishAffinity() }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                val menuAction: () -> Unit = onMenuClick ?: {
                    scope.launch { drawerState.open() }
                }
                
                AppBarKiko(
                    title = title,
                    onBack = onBack,
                    onMenuClick = menuAction
                )
            },
            bottomBar = { BottomBarKiko() },
            content = content
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "LayoutBase Light")
@Composable
fun LayoutBaseKikoPreviewLight() {
    KareerAITheme(darkTheme = false) {
        LayoutKiko(title = "Preview Light") { Box(Modifier.fillMaxSize()) }
    }
}
