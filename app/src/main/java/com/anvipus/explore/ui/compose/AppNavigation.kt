package com.anvipus.explore.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "explore") {
        composable("explore") { ExploreScreen(navController = navController) }
        composable("searchList") { SearchListScreen(navController = navController) }
    }
}