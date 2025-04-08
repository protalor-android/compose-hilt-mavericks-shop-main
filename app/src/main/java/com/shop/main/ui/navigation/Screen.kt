package com.shop.main.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("Home")
    data object Detail : Screen("Detail")
}