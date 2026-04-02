package com.kiko.kareerai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.screen.forgot.ForgotScreen
import com.kiko.kareerai.ui.screen.login.LoginScreen
import com.kiko.kareerai.ui.screen.main.MainScreen
import com.kiko.kareerai.ui.screen.register.EditarUserScreen
import com.kiko.kareerai.ui.screen.register.RegisterScreen
import com.kiko.kareerai.ui.screen.splash.SplashScreen
import com.kiko.kareerai.ui.screen.settings.SettingsScreen
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.ThemeViewModel
import com.kiko.kareerai.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val usuarioViewModel: UsuarioViewModel = viewModel()
            
            val isDarkTheme by themeViewModel.isDarkMode.collectAsState()
            val currentTheme by themeViewModel.themeType.collectAsState()
            
            KareerAITheme(themeType = currentTheme, darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                KikoNavHost(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel,
                    themeViewModel = themeViewModel
                )
            }
        }
    }
}

@Composable
fun KikoNavHost(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    themeViewModel: ThemeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController, viewModel = usuarioViewModel)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, viewModel = usuarioViewModel)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController, viewModel = usuarioViewModel)
        }
        composable(Screen.Main.route) {
            MainScreen(
                navController = navController,
                usuarioViewModel = usuarioViewModel,
                themeViewModel = themeViewModel
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                navController = navController,
                usuarioViewModel = usuarioViewModel,
                themeViewModel = themeViewModel
            )
        }
        composable(Screen.EditarUser.route) {
            EditarUserScreen(
                navController = navController,
                usuarioViewModel = usuarioViewModel
            )
        }
        composable(Screen.Forgot.route) {
            ForgotScreen(navController = navController, viewModel = usuarioViewModel)
        }
    }
}
