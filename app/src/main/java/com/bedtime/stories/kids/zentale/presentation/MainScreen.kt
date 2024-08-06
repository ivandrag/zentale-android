package com.bedtime.stories.kids.zentale.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.createStory.CreateStoryScreen
import com.bedtime.stories.kids.zentale.presentation.home.HomeScreen
import com.bedtime.stories.kids.zentale.presentation.library.LibraryScreen
import com.bedtime.stories.kids.zentale.presentation.profile.ProfileScreen
import com.bedtime.stories.kids.zentale.presentation.story.StoryScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    cameraPermissionState: PermissionState
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomNavigationRoutes = listOf(Screen.Home.route, Screen.Library.route)
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomNavigation = currentRoute in bottomNavigationRoutes
    Scaffold(
        bottomBar = {
            if (showBottomNavigation) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHostContainer(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            cameraPermissionState = cameraPermissionState
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home, Screen.Library
    )
    val insetsPadding = WindowInsets.systemBars.only(WindowInsetsSides.Bottom)
        .asPaddingValues().calculateBottomPadding()
    BottomNavigation(
        modifier = Modifier.padding(bottom = insetsPadding),
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(selected = currentRoute == screen.route, icon = {
                Icon(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.half_content_padding)),
                    imageVector = screen.icon,
                    contentDescription = null,
                    tint = if (currentRoute == screen.route) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onBackground
                )
            }, label = {
                Text(
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (currentRoute == screen.route) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground
                    ), text = stringResource(id = screen.title)
                )
            }, onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }

                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavHostContainer(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    cameraPermissionState: PermissionState
) {
    NavHost(navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Library.route) {
            LibraryScreen(navController)
        }
        composable("profile") { ProfileScreen(navController) }
        composable("createStory") {
            CreateStoryScreen(
                navController,
                hasCameraPermission = cameraPermissionState.status.isGranted,
                onCameraRequiredPermission = cameraPermissionState::launchPermissionRequest
            )
        }
        composable(route = "story") {
            StoryScreen(navController)
        }
    }
}

sealed class Screen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    data object Home : Screen("home", R.string.home_bottom_navigation_text, Icons.Outlined.Home)
    data object Library : Screen(
        "library", R.string.library_bottom_navigation_text, Icons.AutoMirrored.Outlined.LibraryBooks
    )
}