package com.kiko.kareerai.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kiko.kareerai.ui.screen.forgot.ForgotScreen
import com.kiko.kareerai.ui.screen.forgot.RecoverUserScreen
import com.kiko.kareerai.ui.screen.login.LoginScreen
import com.kiko.kareerai.ui.screen.main.MainScreen
import com.kiko.kareerai.ui.screen.register.EditarUserScreen
import com.kiko.kareerai.ui.screen.register.RegisterScreen
import com.kiko.kareerai.ui.screen.settings.SettingsScreen
import com.kiko.kareerai.ui.screen.splash.SplashScreen
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.ThemeViewModel
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel
) {
    val themeViewModel: ThemeViewModel = viewModel()

    val currentTheme by themeViewModel.themeType.collectAsState()
    val isDarkTheme by themeViewModel.isDarkMode.collectAsState()

    KareerAITheme(themeType = currentTheme, darkTheme = isDarkTheme) {
        NavHost(navController = navController, startDestination = Screen.Splash.route) {

            // Splash
            composable(Screen.Splash.route) {
                SplashScreen(navController, usuarioViewModel)
            }

            // Login
            composable(Screen.Login.route) {
                LoginScreen(navController, usuarioViewModel)
            }

            // Main
            composable(Screen.Main.route) {
                MainScreen(navController, usuarioViewModel, themeViewModel)
            }

            // Configurações
            composable(Screen.Settings.route) {
                SettingsScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel,
                    themeViewModel = themeViewModel
                )
            }

            // Cadastro e edição de usuário
            composable(Screen.Register.route) {
                RegisterScreen(navController, usuarioViewModel)
            }
            
            composable(Screen.EditarUser.route) {
                EditarUserScreen(navController, usuarioViewModel)
            }

            // Forgot / Recuperação
            composable(Screen.Forgot.route) {
                ForgotScreen(navController, usuarioViewModel)
            }

            composable(
                route = Screen.RecoverUser.route,
                arguments = listOf(navArgument("codigo") { type = NavType.StringType })
            ) { backStackEntry ->
                val codigo = backStackEntry.arguments?.getString("codigo") ?: ""
                RecoverUserScreen(navController, codigo, usuarioViewModel)
            }
        }
    }
}
