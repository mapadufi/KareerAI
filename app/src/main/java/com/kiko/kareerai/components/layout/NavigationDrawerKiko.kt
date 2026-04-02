package com.kiko.kareerai.components.layout

import androidx.compose.animation.AnimatedVisibility
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
    val subItems: List<KikoDrawerItem>? = null,
    val onClick: (() -> Unit)? = null
)

@Composable
fun NavigationDrawerKiko(
    selectedRoute: String = "",
    onItemClick: (String) -> Unit = {},
    onLogoutClick: () -> Unit = {},
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    items: List<KikoDrawerItem> = emptyList(),
    content: @Composable () -> Unit
) {
    val expandedStates = remember { mutableStateMapOf<String, Boolean>() }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surface,
                drawerContentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.tertiary)
                            .padding(vertical = 32.dp, horizontal = 24.dp)
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
                            val hasSubItems = !item.subItems.isNullOrEmpty()
                            val isExpanded = expandedStates[item.label] ?: false

                            NavigationDrawerItem(
                                label = { Text(item.label) },
                                selected = item.route == selectedRoute,
                                onClick = {
                                    if (hasSubItems) {
                                        expandedStates[item.label] = !isExpanded
                                    } else {
                                        item.onClick?.invoke()
                                        item.route?.let { onItemClick(it) }
                                        scope.launch { drawerState.close() }
                                    }
                                },
                                icon = { Icon(item.icon, contentDescription = null) },
                                badge = {
                                    if (hasSubItems) {
                                        Icon(
                                            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = null
                                        )
                                    }
                                },
                                shape = MaterialTheme.shapes.medium,
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                    selectedIconColor = MaterialTheme.colorScheme.tertiary,
                                    selectedTextColor = MaterialTheme.colorScheme.tertiary,
                                    unselectedIconColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                                    unselectedTextColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
                                ),
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )

                            if (hasSubItems) {
                                AnimatedVisibility(visible = isExpanded) {
                                    Column(modifier = Modifier.padding(start = 24.dp)) {
                                        item.subItems?.forEach { subItem ->
                                            NavigationDrawerItem(
                                                label = { Text(subItem.label, style = MaterialTheme.typography.bodyMedium) },
                                                selected = subItem.route == selectedRoute,
                                                onClick = { 
                                                    subItem.onClick?.invoke()
                                                    subItem.route?.let { onItemClick(it) } 
                                                    scope.launch { drawerState.close() }
                                                },
                                                icon = { Icon(subItem.icon, contentDescription = null, modifier = Modifier.size(18.dp)) },
                                                shape = MaterialTheme.shapes.medium,
                                                colors = NavigationDrawerItemDefaults.colors(
                                                    selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
                                                    selectedIconColor = MaterialTheme.colorScheme.tertiary,
                                                    selectedTextColor = MaterialTheme.colorScheme.tertiary,
                                                    unselectedIconColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                                                    unselectedTextColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
                                                ),
                                                modifier = Modifier.padding(vertical = 2.dp).padding(NavigationDrawerItemDefaults.ItemPadding)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(8.dp))
                        HorizontalDividerKiko(modifier = Modifier.padding(horizontal = 12.dp))
                        Spacer(Modifier.height(8.dp))

                        NavigationDrawerItem(
                            label = { Text("Sair") },
                            selected = false,
                            onClick = {
                                onLogoutClick()
                                scope.launch { drawerState.close() }
                            },
                            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null) },
                            shape = MaterialTheme.shapes.medium,
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
        gesturesEnabled = items.isNotEmpty(),
        content = content
    )
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Drawer Light")
@Composable
fun NavigationDrawerKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        NavigationDrawerKiko(
            drawerState = drawerState,
            items = listOf(
                KikoDrawerItem("Início", Icons.Default.Home, Screen.Main.route),
                KikoDrawerItem("Configurações", Icons.Default.Settings, Screen.Settings.route)
            )
        ) {
            Box(Modifier.fillMaxSize())
        }
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Drawer Dark")
@Composable
fun NavigationDrawerKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        NavigationDrawerKiko(
            drawerState = drawerState,
            items = listOf(
                KikoDrawerItem("Início", Icons.Default.Home, Screen.Main.route),
                KikoDrawerItem("Configurações", Icons.Default.Settings, Screen.Settings.route)
            )
        ) {
            Box(Modifier.fillMaxSize())
        }
    }
}
