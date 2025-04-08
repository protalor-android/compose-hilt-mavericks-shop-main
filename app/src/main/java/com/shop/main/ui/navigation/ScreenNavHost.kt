package com.shop.main.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shop.main.ui.screen.detail.DetailScreen
import com.shop.main.ui.screen.home.HomeScreen

@Composable
fun ScreenNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateDetail = { detailUrl ->
                    navController.navigate(route = "${Screen.Detail.route}/${Uri.encode(detailUrl)}")
                }
            )
        }
        composable(
            route = "${Screen.Detail.route}/{detailUrl}",
            arguments = listOf(
                navArgument("detailUrl") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("detailUrl")?.let {
                DetailScreen(
                    detailUrl = it,
                    navigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}