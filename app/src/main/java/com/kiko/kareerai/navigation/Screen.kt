package com.kiko.kareerai.navigation
sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Forgot : Screen("forgot")
    object RecoverUser : Screen("recover_user/{codigo}") {
        fun createRoute(codigo: String) = "recover_user/$codigo"
    }
    object EditUser : Screen("edit_user")
    object EditarUser : Screen("editar_user")
    object Main : Screen("main")
    object Settings : Screen("settings")

}
