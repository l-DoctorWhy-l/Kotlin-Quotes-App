package ru.rodipit.kotlinquotesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.NavigationEvent
import com.example.navigation.api.Route
import com.example.sign_in.api.SignInScreenWrapper
import com.example.sign_up.api.SignUpScreenWrapper
import com.example.splash.api.SplashScreenWrapper
import kotlinx.coroutines.flow.map
import org.koin.android.ext.android.inject
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.kotlinquotesapp.navigation.BottomNavigationBar
import ru.rodipit.kotlinquotesapp.navigation.NavHostContainer
import ru.rodipit.profile.api.ProfileScreenWrapper
import ru.rodipit.quote_details.api.QuoteDetailsScreenWrapper
import ru.rodipit.settings.api.SettingsScreenWrapper
import ru.rodipit.utils.ThemeManager


class MainActivity : ComponentActivity() {


    private val appNavigator by inject<AppNavigator>()
    private val themeManager by inject<ThemeManager>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val theme = themeManager.themeFlow
        enableEdgeToEdge()
        setContent {
            val systemTheme = isSystemInDarkTheme()
            AppTheme(
                darkTheme = theme.map {
                    when(it) {
                        "light" -> false
                        "dark" -> true
                        else -> systemTheme
                    }
                }.collectAsState(systemTheme).value
            ) {
                val appNavController = rememberNavController()
                val navController = rememberNavController()


                val appNavigator = remember { appNavigator }

                val navigationEvent by appNavigator.navigationEvent.collectAsState()

                LaunchedEffect(navigationEvent) {
                    navigationEvent?.let { event ->
                        when(event) {
                            is NavigationEvent.NavigateTo -> {
                                appNavController.navigate(event.route)
                            }
                            is NavigationEvent.PopUpTo -> {
                                appNavController.navigate(event.route) {
                                    popUpTo(event.route) {
                                        inclusive = false
                                    }
                                }
                            }
                            is NavigationEvent.PopBackStack -> {
                                appNavController.popBackStack()
                            }
                        }
                        appNavigator.resetNavigation()
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { appPadding ->
                    NavHost(
                        navController = appNavController,
                        startDestination = Route.Main,
                        modifier = Modifier
                            .fillMaxSize(),
                        builder = {
                            composable<Route.Main> {
                                Scaffold(
                                    modifier = Modifier.fillMaxSize(),
                                    bottomBar = {
                                        BottomNavigationBar(
                                            navController = navController
                                        )
                                    }
                                ) { mainScreenInnerPadding ->
                                    NavHostContainer(
                                        navController = navController,
                                        padding = mainScreenInnerPadding,
                                    )
                                }
                            }

                            composable<Route.Splash> {
                                SplashScreenWrapper(
                                    modifier = Modifier.padding(appPadding),
                                )
                            }

                            composable<Route.Profile> {
                                ProfileScreenWrapper(
                                    modifier = Modifier.padding(appPadding),
                                )
                            }

                            composable<Route.Settings> {
                                SettingsScreenWrapper(
                                    modifier = Modifier.padding(appPadding),
                                )
                            }

                            composable<Route.QuoteDetails> {
                                val args = it.toRoute<Route.QuoteDetails>()
                                QuoteDetailsScreenWrapper(
                                    id = args.id,
                                    modifier = Modifier.padding(appPadding),
                                )
                            }

                            navigation(
                                startDestination = Route.SignIn::class,
                                route = Route.Auth::class,
                            ) {
                                composable<Route.SignUp> {
                                    SignUpScreenWrapper()
                                }
                                composable<Route.SignIn> {
                                    SignInScreenWrapper()
                                }
                            }
                        }
                    )
                }

            }
        }
    }
}

