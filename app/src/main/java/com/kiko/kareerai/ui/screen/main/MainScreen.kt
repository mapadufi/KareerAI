package com.kiko.kareerai.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kiko.kareerai.components.layout.KikoDrawerItem
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.toast.KikoDeleteToast
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.theme.*
import com.kiko.kareerai.viewmodel.ThemeViewModel
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun MainScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    themeViewModel: ThemeViewModel = viewModel()
) {
    var toastMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Observa DarkMode para definir background
    val isDarkTheme by themeViewModel.isDarkMode.collectAsState()
    val backgroundColor = if (isDarkTheme) Dark950 else BrancoApp

    val iconSize = 120.dp

    // DrawerItems usando o novo padrão KikoDrawerItem
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

    LayoutKiko(
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Kareer AI",
        drawerItems = drawerItems
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
        ) {

            // 🔹 Conteúdo do topo: texto + imagem
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // Texto centralizado
                Text(
                    text = "Kareer AI",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(28.dp)) // espaço entre texto e imagem

                // Icon Pets
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Dog Icon",
                    modifier = Modifier.size(iconSize),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 200.dp) // espaço para o topo
            ) {
                // Aqui você pode inserir a barra de pesquisa ou o conteúdo principal
            }

            // Toast de exclusão (preto) CENTRALIZADO NO TOPO
            if (toastMessage.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    KikoDeleteToast(message = toastMessage)
                }

                LaunchedEffect(toastMessage) {
                    kotlinx.coroutines.delay(3000)
                    toastMessage = ""
                }
            }
        }
    }
}
