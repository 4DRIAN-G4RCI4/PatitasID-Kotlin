package com.example.patitasid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.patitasid.ui.screens.auth.LoginScreen
import com.example.patitasid.ui.screens.auth.RegisterScreen
import com.example.patitasid.ui.screens.carnet.CarnetDetalleScreen
import com.example.patitasid.ui.screens.demo.DemoScreen
import com.example.patitasid.ui.screens.home.HomeScreen
import com.example.patitasid.ui.screens.splash.SplashScreen
import com.example.patitasid.ui.screens.veterinaria.VeterinariaDetalleScreen

@Composable
fun PatitasNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(onNavigate = {
                navController.navigate(Screen.Login.route) { popUpTo(Screen.Splash.route) { inclusive = true } }
            })
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } }
                },
                onRegister = { navController.navigate(Screen.Register.route) },
                onDemo = { navController.navigate(Screen.Demo.route) }
            )
        }

        composable(Screen.Demo.route) {
            DemoScreen(
                onRegister = { navController.navigate(Screen.Register.route) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onSuccess = {
                    navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onCarnet = { id -> navController.navigate(Screen.CarnetDetalle.withId(id)) },
                onVeterinaria = { id -> navController.navigate(Screen.VeterinariaDetalle.withId(id)) }
            )
        }

        composable(
            route = Screen.CarnetDetalle.route,
            arguments = listOf(navArgument("mascotaId") { type = NavType.StringType })
        ) {
            CarnetDetalleScreen(
                mascotaId = it.arguments?.getString("mascotaId") ?: "",
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.VeterinariaDetalle.route,
            arguments = listOf(navArgument("vetId") { type = NavType.StringType })
        ) {
            VeterinariaDetalleScreen(
                vetId = it.arguments?.getString("vetId") ?: "",
                onBack = { navController.popBackStack() }
            )
        }
    }
}
