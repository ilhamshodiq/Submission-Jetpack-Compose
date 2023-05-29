package com.the12smb.submissionjetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailContent : Screen("home/{contentId}") {
        fun createRoute(contentId: Long) = "home/$contentId"
    }
}