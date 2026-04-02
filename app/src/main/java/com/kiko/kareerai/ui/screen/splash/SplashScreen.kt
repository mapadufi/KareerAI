package com.kiko.kareerai.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.ui.tooling.preview.Preview
import com.kiko.kareerai.components.progress.ProgressKiko
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.UsuarioViewModel


@Composable
fun SplashScreen(
    navController: NavController? = null,  // Opcional para Preview
    viewModel: UsuarioViewModel? = null    // Opcional para Preview
) {
    val gradientColors = listOf(
        MaterialTheme.colorScheme.outline,
        MaterialTheme.colorScheme.outlineVariant
    )

    val appName = "Kareer AI"
    val iconSize = 120.dp
    val progressSize = 64.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = gradientColors))
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                text = appName,
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Dog Icon",
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colorScheme.onTertiary
            )

            /*            Image(
                            painter = painterResource(id = R.drawable.fucinho),
                            contentDescription = "App Icon",
                            modifier = Modifier.size(iconSize),
                            contentScale = ContentScale.Fit
                        )*/

            Spacer(modifier = Modifier.height(32.dp))

            ProgressKiko(
                modifier = Modifier.size(progressSize),
                color = MaterialTheme.colorScheme.onTertiary,
                trackColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.3f)
            )
        }
    }

    // Navegação automática após splash
    if (navController != null && viewModel != null) {
        LaunchedEffect(Unit) {
            delay(2000) // Splash de 2 segundos

            // Verifica se existe usuário no banco
            val usuario = viewModel.currentUser.value
            if (usuario != null) {
                // Usuário existe → Login
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            } else {
                // Usuário não existe → Registro de usuário
                navController.navigate(Screen.Register.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
    }
}


// Previews apenas para UI, sem lógica de navegação
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun SplashScreenLightPreview() {
    KareerAITheme (darkTheme = false) {
        SplashScreen()
    }
}

// Previews apenas para UI, sem lógica de navegação
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun SplashScreenDarkPreview() {
    KareerAITheme (darkTheme = true) {
        SplashScreen()
    }
}
