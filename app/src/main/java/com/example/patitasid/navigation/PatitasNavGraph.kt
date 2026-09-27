package com.example.patitasid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.patitasid.ui.screens.adopciones.AdopcionesScreen
import com.example.patitasid.ui.screens.auth.LoginScreen
import com.example.patitasid.ui.screens.auth.RegisterScreen
import com.example.patitasid.ui.screens.campanas.CampanasScreen
import com.example.patitasid.ui.screens.carnet.CarnetDetalleScreen
import com.example.patitasid.ui.screens.home.HomeScreen
import com.example.patitasid.ui.screens.mapa.MapaScreen
import com.example.patitasid.ui.screens.mascotas.MisMascotasScreen
import com.example.patitasid.ui.screens.noticias.NoticiasScreen
import com.example.patitasid.ui.screens.perfil.PerfilScreen
import com.example.patitasid.ui.screens.splash.SplashScreen
import com.example.patitasid.ui.screens.veterinaria.VeterinariaDetalleScreen

@Composable
fun PatitasNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(onNavigate = { navController.navigate(Screen.Login.route) })
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } } },
                onRegister = { navController.navigate(Screen.Register.route) }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.MisMascotas.route) {
            MisMascotasScreen(
                onMascotaClick = { id -> navController.navigate(Screen.CarnetDetalle.withId(id)) }
            )
        }

        composable(
            route = Screen.CarnetDetalle.route,
            arguments = listOf(navArgument("mascotaId") { type = NavType.StringType })
        ) { backStack ->
            CarnetDetalleScreen(
                mascotaId = backStack.arguments?.getString("mascotaId") ?: "",
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Mapa.route) {
            MapaScreen(
                onVeterinariaClick = { id -> navController.navigate(Screen.VeterinariaDetalle.withId(id)) }
            )
        }

        composable(
            route = Screen.VeterinariaDetalle.route,
            arguments = listOf(navArgument("vetId") { type = NavType.StringType })
        ) { backStack ->
            VeterinariaDetalleScreen(
                vetId = backStack.arguments?.getString("vetId") ?: "",
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Adopciones.route) {
            AdopcionesScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Campanas.route) {
            CampanasScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Noticias.route) {
            NoticiasScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Perfil.route) {
            PerfilScreen(onBack = { navController.popBackStack() })
        }
    }
}
