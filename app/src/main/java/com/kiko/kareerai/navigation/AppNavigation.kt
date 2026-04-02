package com.kiko.kareerai.navigation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.kiko.kareerai.data.local.database.KareerAIDatabase
import com.kiko.kareerai.data.remote.ai.AIRepository
import com.kiko.kareerai.data.remote.ai.AIService
import com.kiko.kareerai.data.remote.jobs.JobRepository
import com.kiko.kareerai.data.remote.jobs.JobService
import com.kiko.kareerai.data.remote.network.RetrofitClient
import com.kiko.kareerai.data.repository.CurriculoRepository
import com.kiko.kareerai.domain.usecase.*
import com.kiko.kareerai.ui.screen.curriculum.CurriculumResultScreen
import com.kiko.kareerai.ui.screen.curriculum.UploadCurriculumScreen
import com.kiko.kareerai.ui.screen.forgot.ForgotScreen
import com.kiko.kareerai.ui.screen.forgot.RecoverUserScreen
import com.kiko.kareerai.ui.screen.jobs.JobDetailScreen
import com.kiko.kareerai.ui.screen.jobs.JobListScreen
import com.kiko.kareerai.ui.screen.jobs.SavedJobsScreen
import com.kiko.kareerai.ui.screen.login.LoginScreen
import com.kiko.kareerai.ui.screen.main.MainScreen
import com.kiko.kareerai.ui.screen.register.EditarUserScreen
import com.kiko.kareerai.ui.screen.register.RegisterScreen
import com.kiko.kareerai.ui.screen.settings.SettingsScreen
import com.kiko.kareerai.ui.screen.splash.SplashScreen
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    
    val database = KareerAIDatabase.getDatabase(application)
    val jobDao = database.jobDao()
    val curriculoDao = database.curriculoDao()
    
    val curriculoRepository = remember { CurriculoRepository(curriculoDao) }
    
    val aiService = remember { RetrofitClient.getRetrofitAI().create(AIService::class.java) }
    val aiRepository = remember { AIRepository(aiService) }
    
    val jobService = remember { RetrofitClient.getRetrofitJobs().create(JobService::class.java) }
    val jobRepository = remember { JobRepository(jobService, aiRepository) }
    
    val themeViewModel: ThemeViewModel = viewModel()
    
    val curriculumViewModel: CurriculumViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CurriculumViewModel(
                    analisarCurriculoUseCase = AnalisarCurriculoUseCase(aiRepository),
                    curriculoRepository = curriculoRepository,
                    aiRepository = aiRepository
                ) as T
            }
        }
    )
    
    val jobViewModel: JobViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return JobViewModel(
                    buscarVagasUseCase = BuscarVagasUseCase(jobRepository),
                    salvarVagaUseCase = SalvarVagaUseCase(jobDao),
                    obterVagasSalvasUseCase = ObterVagasSalvasUseCase(jobDao),
                    deletarVagaUseCase = DeletarVagaUseCase(jobDao)
                ) as T
            }
        }
    )

    val currentUser by usuarioViewModel.currentUser.collectAsState()
    val currentTheme by themeViewModel.themeType.collectAsState()
    val isDarkTheme by themeViewModel.isDarkMode.collectAsState()
    val geminiApiKey by themeViewModel.geminiApiKey.collectAsState()

    val perfil by curriculumViewModel.perfil.collectAsState()
    val curriculumLoading by curriculumViewModel.loading.collectAsState()
    val jobs by jobViewModel.jobs.collectAsState()
    val savedJobs by jobViewModel.savedJobs.collectAsState()
    val selectedJob by jobViewModel.selectedJob.collectAsState()

    val pitch by curriculumViewModel.pitch.collectAsState()
    val isGeneratingPitch by curriculumViewModel.isGeneratingPitch.collectAsState()

    LaunchedEffect(perfil) {
        if (perfil != null) {
            navController.navigate(Screen.CurriculumResult.route)
        }
    }

    KareerAITheme(themeType = currentTheme, darkTheme = isDarkTheme) {
        NavHost(navController = navController, startDestination = Screen.Splash.route) {
            composable(Screen.Splash.route) {
                SplashScreen(navController, usuarioViewModel)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController, usuarioViewModel)
            }
            composable(Screen.Main.route) {
                MainScreen(navController, usuarioViewModel, themeViewModel)
            }
            composable(Screen.Settings.route) {
                SettingsScreen(navController, usuarioViewModel, themeViewModel)
            }
            composable(Screen.Register.route) {
                RegisterScreen(navController, usuarioViewModel)
            }
            composable(Screen.EditarUser.route) {
                EditarUserScreen(navController, usuarioViewModel)
            }
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

            composable(Screen.UploadCurriculum.route) {
                UploadCurriculumScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel,
                    isLoading = curriculumLoading,
                    onAnalisarCurriculo = { texto ->
                        currentUser?.let { user ->
                            curriculumViewModel.analisarCurriculo(geminiApiKey ?: "", texto, user.id)
                        }
                    }
                )
            }

            composable(Screen.CurriculumResult.route) {
                perfil?.let { p ->
                    CurriculumResultScreen(
                        navController = navController,
                        usuarioViewModel = usuarioViewModel,
                        perfil = p,
                        onBuscarVagas = { perfilAtualizado ->
                            jobViewModel.buscarVagas(geminiApiKey ?: "", perfilAtualizado)
                            navController.navigate(Screen.JobList.route)
                        }
                    )
                } ?: Box(modifier = Modifier.fillMaxSize()) {
                    LaunchedEffect(Unit) { navController.navigate(Screen.UploadCurriculum.route) }
                }
            }

            composable(Screen.JobList.route) {
                JobListScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel,
                    jobs = jobs,
                    onClickJob = { job ->
                        jobViewModel.selectJob(job)
                        navController.navigate(Screen.JobDetail.route)
                    }
                )
            }

            composable(Screen.JobDetail.route) {
                selectedJob?.let { job ->
                    JobDetailScreen(
                        navController = navController,
                        usuarioViewModel = usuarioViewModel,
                        job = job,
                        pitch = pitch,
                        isGeneratingPitch = isGeneratingPitch,
                        onSalvar = { j -> jobViewModel.salvarVaga(j) },
                        onGerarPitch = {
                            currentUser?.let { user ->
                                curriculumViewModel.gerarPitchIA(geminiApiKey ?: "", user.id, job.title, job.description)
                            }
                        },
                        onLimparPitch = { curriculumViewModel.limparPitch() }
                    )
                }
            }

            composable(Screen.SavedJobs.route) {
                LaunchedEffect(Unit) { jobViewModel.carregarVagasSalvas() }
                SavedJobsScreen(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel,
                    jobs = savedJobs,
                    onRemoveJob = { id -> jobViewModel.deletarVaga(id) },
                    onClickJob = { job ->
                        jobViewModel.selectJob(job)
                        navController.navigate(Screen.JobDetail.route)
                    }
                )
            }
        }
    }
}
