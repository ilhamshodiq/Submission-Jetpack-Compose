package com.the12smb.submissionjetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.the12smb.submissionjetpackcompose.ui.navigation.NavigationItem
import com.the12smb.submissionjetpackcompose.ui.navigation.Screen
import com.the12smb.submissionjetpackcompose.ui.screen.detail.DetailScreen
import com.the12smb.submissionjetpackcompose.ui.screen.home.HomeScreen
import com.the12smb.submissionjetpackcompose.ui.screen.about.AboutScreen

@Composable
fun DotaIntelligenceApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailContent.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { contentId ->
                        navController.navigate(Screen.DetailContent.createRoute(contentId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                AboutScreen(onBackClick = { navController.navigateUp() })
            }
            composable(
                route = Screen.DetailContent.route,
                arguments = listOf(navArgument("contentId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("contentId") ?: -1L
                DetailScreen(
                    contentId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }

}

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    BottomNavigation (
        modifier = modifier
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(com.the12smb.submissionjetpackcompose.R.string.home_menu),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(com.the12smb.submissionjetpackcompose.R.string.about_page),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
