package com.bedtime.stories.kids.zentale.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.bedtime.stories.kids.zentale.presentation.createStory.CreateStoryScreen
import com.bedtime.stories.kids.zentale.presentation.home.HomeScreen
import com.bedtime.stories.kids.zentale.presentation.login.LoginScreen
import com.bedtime.stories.kids.zentale.presentation.profile.ProfileScreen
import com.bedtime.stories.kids.zentale.presentation.utils.ZentaleTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setContent {
            ZentaleTheme(
                darkTheme = true
            ) {
                Surface {
                    NavigationComponent()
                }
            }
        }
    }
}

@Composable
fun NavigationComponent() {
    val mainViewModel: MainViewModel = koinViewModel()
    val navController = rememberNavController()

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
            route = "loggedIn", startDestination = "home"
        ) {
            composable("home") { HomeScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
            composable("createStory") { CreateStoryScreen(navController) }
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

