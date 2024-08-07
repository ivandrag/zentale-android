package com.bedtime.stories.kids.zentale.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.bedtime.stories.kids.zentale.presentation.login.LoginScreen
import com.bedtime.stories.kids.zentale.presentation.utils.ZentaleTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setContent {
            ZentaleTheme(
                darkTheme = true
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComponent()
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavigationComponent() {
    val mainViewModel: MainViewModel = koinViewModel()
    val navController = rememberNavController()
    val cameraPermissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(mainViewModel) {
        mainViewModel.event.collect { event ->
            handleEvent(event, navController)
        }
    }

    NavHost(
        navController = navController, startDestination = "root"
    ) {
        composable("root") { }
        navigation(
            route = "loggedOut", startDestination = "login"
        ) {
            composable("login") { LoginScreen() }
        }
        navigation(
            route = "loggedIn", startDestination = "main"
        ) {
            composable("main") { MainScreen(cameraPermissionState) }
        }
    }
}

private fun handleEvent(event: MainViewModel.Event, navController: NavHostController) {
    when (event) {
        MainViewModel.Event.LoggedIn -> {
            navController.navigate("loggedIn") {
                popUpTo("root") { inclusive = true }
            }
        }

        MainViewModel.Event.LoggedOut -> {
            navController.navigate("loggedOut") {
                popUpTo("root") { inclusive = true }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZentaleTheme(darkTheme = true) {
        NavigationComponent()
    }
}

