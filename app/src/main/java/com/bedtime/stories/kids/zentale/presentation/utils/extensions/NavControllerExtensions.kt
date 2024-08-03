package com.bedtime.stories.kids.zentale.presentation.utils.extensions

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    Log.d("NavControllerExt", "Navigating to $route with args: $args")
    this.navigate(route, navOptions, navigatorExtras)
    this.currentBackStackEntry?.arguments?.putAll(args)
}
