package com.kiko.kareerai.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.components.divider.HorizontalDividerKiko
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.theme.KareerAITheme
import kotlinx.coroutines.launch

data class KikoDrawerItem(
    val label: String,
    val icon: ImageVector,
    val route: String? = null,
    val onClick: (() -> Unit)? = null
)

// FONTE ÚNICA DA VERDADE PARA O MENU
fun getDrawerItems(): List<KikoDrawerItem> = listOf(
    KikoDrawerItem("Início", Icons.Default.Home, Screen.Main.route),
    KikoDrawerItem("Enviar Currículo", Icons.Default.Description, Screen.UploadCurriculum.route),
    KikoDrawerItem("Buscar Vagas", Icons.Default.Search, Screen.JobList.route),
    KikoDrawerItem("Vagas Salvas", Icons.Default.Star, Screen.SavedJobs.route),
    KikoDrawerItem("Editar Usuário", Icons.Default.Person, Screen.EditarUser.route),
    KikoDrawerItem("Configurações", Icons.Default.Settings, Screen.Settings.route)
)

@Composable
fun NavigationDrawerKiko(
    selectedRoute: String = "",
    onItemClick: (String) -> Unit = {},
    onLogoutClick: () -> Unit = {},
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    items: List<KikoDrawerItem> = getDrawerItems(),
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    // HEADER
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.tertiary)
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Kareer AI",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                        items.forEach { item ->
                            NavigationDrawerItem(
                                label = { Text(item.label) },
                                selected = item.route == selectedRoute,
                                onClick = {
                                    item.route?.let { onItemClick(it) }
                                    item.onClick?.invoke()
                                    scope.launch { drawerState.close() }
                                },
                                icon = { Icon(item.icon, contentDescription = null) },
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                    selectedTextColor = MaterialTheme.colorScheme.tertiary,
                                    selectedIconColor = MaterialTheme.colorScheme.tertiary,
                                    unselectedIconColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                                    unselectedTextColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
                                ),
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }

                        Spacer(Modifier.height(8.dp))
                        HorizontalDividerKiko()
                        Spacer(Modifier.height(8.dp))

                        // LOGOUT (SAIR)
                        NavigationDrawerItem(
                            label = { Text("Sair") },
                            selected = false,
                            onClick = {
                                onLogoutClick()
                                scope.launch { drawerState.close() }
                            },
                            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null) },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedIconColor = MaterialTheme.colorScheme.error,
                                unselectedTextColor = MaterialTheme.colorScheme.error
                            ),
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        gesturesEnabled = true,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    KareerAITheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        NavigationDrawerKiko(drawerState = drawerState) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}
