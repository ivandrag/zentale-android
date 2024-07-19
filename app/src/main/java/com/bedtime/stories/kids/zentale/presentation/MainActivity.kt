package com.bedtime.stories.kids.zentale.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bedtime.stories.kids.zentale.presentation.login.LoginScreen
import com.bedtime.stories.kids.zentale.presentation.utils.ZentaleTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LaunchedEffect(viewModel) {
                viewModel.event.collect { event ->
                    handleEvent(event, navController)
                }
            }
            ZentaleTheme(
                darkTheme = true
            ) {
                Surface(color = MaterialTheme.colors.background) {
                    NavigationComponent()
                }
            }
        }
    }

    private fun handleEvent(event: MainViewModel.Event, navController: NavHostController) {
        when (event) {
            MainViewModel.Event.LoggedIn -> {
                println("##Logged in")
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
            MainViewModel.Event.LoggedOut -> {
                println("##Logged out")
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen() }
        composable("home") { HomeScreen() }
    }
}

@Composable
fun HomeScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {  },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Go to Details")
        }
    }
}

@Composable
fun DetailsScreen(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Go to Home")
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

