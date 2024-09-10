package ru.rodipit.kotlinquotesapp.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.rodipit.favourites.api.FavouritesScreenWrapper
import ru.rodipit.kotlinquotesapp.R
import ru.rodipit.kotlinquotesapp.navigation.models.Routes
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.main_screen.api.MainScreenWrapper

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = padding),
        builder = {
            composable(Routes.Home.route) {
                MainScreenWrapper()
            }

            composable(Routes.Favourites.route) {
                FavouritesScreenWrapper()
            }
        }
    )

}


@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val bottomNavItems = remember {
        listOf(
            BottomNavItem(
                label = "Home",
                icon = R.drawable.baseline_home_24,
                route = Routes.Home.route,
            ),
            BottomNavItem(
                label = "Favourites",
                icon = R.drawable.baseline_favorite_24,
                route = Routes.Favourites.route,
            )
        )
    }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        bottomNavItems.forEach { navItem ->

            NavigationBarItem(
                selected = currentRoute == navItem.route,
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                ),
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        modifier = Modifier.size(32.dp),
                        contentDescription = null,
                    )
                }
            )
        }
    }

}

internal data class BottomNavItem(
    val label: String,
    @DrawableRes val icon: Int,
    val route: String,
)