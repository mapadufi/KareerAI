package com.kiko.kareerai.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.layout.KikoDrawerItem
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.switch.KikoSwitchWithIconInsideThumb
import com.kiko.kareerai.components.toast.KikoErrorToast
import com.kiko.kareerai.components.toast.KikoSuccessToast
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.theme.*
import com.kiko.kareerai.viewmodel.ThemeViewModel
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun SettingsScreen(
    navController: NavController? = null,
    usuarioViewModel: UsuarioViewModel? = null,
    themeViewModel: ThemeViewModel = viewModel(),
) {
    val scrollState = rememberScrollState()
    val selectedTheme by themeViewModel.themeType.collectAsState()
    val isDarkTheme by themeViewModel.isDarkMode.collectAsState()
    val backgroundColor: Color = if (isDarkTheme) Dark950 else BrancoApp
    val colorScheme = MaterialTheme.colorScheme

    var toastMessage by remember { mutableStateOf("") }
    var isSuccessToast by remember { mutableStateOf(true) }

    val drawerItems = listOf(
        KikoDrawerItem(
            label = "Início",
            icon = Icons.Default.Home,
            route = Screen.Main.route
        ),
        KikoDrawerItem(
            label = "Editar Usuário",
            icon = Icons.Default.Person,
            route = Screen.EditarUser.route
        ),
        KikoDrawerItem(
            label = "Configurações",
            icon = Icons.Default.Settings,
            route = Screen.Settings.route
        )
    )

    LayoutKiko (
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Configurações",
        drawerItems = drawerItems
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // -------------------- Tema Light/Dark --------------------
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = colorScheme.onPrimary),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Tema", style = MaterialTheme.typography.titleMedium, color = colorScheme.tertiary)
                        Spacer(Modifier.height(4.dp))
                        Text("Escolha entre Light/Dark", style = MaterialTheme.typography.bodyMedium, color = colorScheme.tertiary)
                    }

                    KikoSwitchWithIconInsideThumb(
                        checked = isDarkTheme,
                        onCheckedChange = { themeViewModel.setDarkMode(it) }
                    )
                }
            }

            // -------------------- Seleção de cores --------------------
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = colorScheme.onPrimary),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Cores", style = MaterialTheme.typography.titleMedium, color = colorScheme.tertiary)
                    Spacer(Modifier.height(4.dp))
                    Text("Selecione a cor do aplicativo", style = MaterialTheme.typography.bodyMedium, color = colorScheme.tertiary)
                    Spacer(Modifier.height(12.dp))

                    val themes = listOf(
                        AppThemeType.PADRAO to Primary900,
                        AppThemeType.VERDE to Success900,
                        AppThemeType.VERMELHO to Error900,
                        AppThemeType.ROXO to Roxo900
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        themes.forEach { (theme, color) ->
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(color, shape = MaterialTheme.shapes.medium)
                                    .clickable { themeViewModel.setTheme(theme) },
                                contentAlignment = Alignment.Center
                            ) {
                                if (selectedTheme == theme) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Selecionado",
                                        tint = colorScheme.onTertiary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // -------------------- Usuário --------------------
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = colorScheme.onPrimary),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Usuário", style = MaterialTheme.typography.titleMedium, color = colorScheme.tertiary)
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Editar seu usuário",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorScheme.tertiary,
                            modifier = Modifier.weight(1f)
                        )
                        KikoExtraButton(
                            text = "Editar",
                            onClick = { navController?.navigate(Screen.EditarUser.route) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // -------------------- Toast --------------------
            if (toastMessage.isNotEmpty()) {
                if (isSuccessToast) {
                    KikoSuccessToast(message = toastMessage)
                } else {
                    KikoErrorToast(message = toastMessage)
                }
            }

            LaunchedEffect(toastMessage) {
                if (toastMessage.isNotEmpty()) {
                    kotlinx.coroutines.delay(3000)
                    toastMessage = ""
                }
            }
        }
    }
}
