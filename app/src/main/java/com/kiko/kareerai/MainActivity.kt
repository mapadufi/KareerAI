package com.kiko.kareerai

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.kiko.kareerai.navigation.AppNavigation
import com.kiko.kareerai.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val usuarioViewModel: UsuarioViewModel = viewModel()
            val navController = rememberNavController()
            
            AppNavigation(
                navController = navController,
                usuarioViewModel = usuarioViewModel
            )
        }
    }
}
