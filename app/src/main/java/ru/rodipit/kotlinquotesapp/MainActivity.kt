package ru.rodipit.kotlinquotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.NavigationEvent
import com.example.navigation.api.Route
import com.example.sign_in.api.SignInScreenWrapper
import com.example.sign_up.api.SignUpScreenWrapper
import com.example.splash.api.SplashScreenWrapper
import org.koin.android.ext.android.inject
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.kotlinquotesapp.navigation.BottomNavigationBar
import ru.rodipit.kotlinquotesapp.navigation.NavHostContainer
import ru.rodipit.profile.api.ProfileScreenWrapper


class MainActivity : ComponentActivity() {


    private val appNavigator by inject<AppNavigator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val appNavController = rememberNavController()
                val navController = rememberNavController()


                val appNavigator = remember { appNavigator }

                val navigationEvent by appNavigator.navigationEvent.collectAsState()

                LaunchedEffect(navigationEvent) {
                    navigationEvent?.let { event ->
                        when(event) {
                            is NavigationEvent.NavigateTo -> {
                                appNavController.navigate(event.route.value)
                            }
                            is NavigationEvent.PopUpTo -> {
                                appNavController.navigate(event.route.value) {
                                    popUpTo(event.route.value) {
                                        inclusive = false
                                    }
                                }
                            }
                            NavigationEvent.PopBackStack -> {
                                appNavController.popBackStack()
                            }
                        }
                        appNavigator.resetNavigation()
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = appNavController,
                        startDestination = Route.Main.value,
                        modifier = Modifier
                            .fillMaxSize(),
                        builder = {
                            composable(Route.Main.value) {
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

                            composable(Route.Splash.value) {
                                SplashScreenWrapper()
                            }
                            composable(Route.Profile.value) {
                                ProfileScreenWrapper()
                            }

                            navigation(
                                startDestination = Route.SignIn.value,
                                route = Route.Auth.value,
                            ) {
                                composable(Route.SignUp.value) {
                                    SignUpScreenWrapper()
                                }
                                composable(Route.SignIn.value) {
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

