package com.kiko.kareerai.navigation
sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Forgot : Screen("forgot")

    object RecoverUser : Screen("recover_user/{codigo}") {
        fun createRoute(codigo: String) = "recover_user/$codigo"
    }

    object EditarUser : Screen("editar_user")

    object Main : Screen("main")
    object Settings : Screen("settings")

    // 🔥 NOVAS TELAS DO APP
    object Dashboard : Screen("dashboard")
    object UploadCurriculum : Screen("upload_curriculum")
    object CurriculumResult : Screen("curriculum_result")
    object JobList : Screen("job_list")
    object JobDetail : Screen("job_detail")
    object SavedJobs : Screen("saved_jobs")
}