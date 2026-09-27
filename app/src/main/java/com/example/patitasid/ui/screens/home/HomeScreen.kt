package com.example.patitasid.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.patitasid.navigation.Screen
import com.example.patitasid.ui.screens.adopciones.AdopcionesScreen
import com.example.patitasid.ui.screens.mapa.MapaScreen
import com.example.patitasid.ui.screens.mascotas.MisMascotasScreen
import com.example.patitasid.ui.screens.noticias.NoticiasScreen
import com.example.patitasid.ui.screens.dashboard.DashboardScreen

data class BottomNavItem(val label: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun HomeScreen(navController: NavController) {
    val bottomNavController = rememberNavController()

    val items = listOf(
        BottomNavItem("Inicio", "dashboard", Icons.Filled.Home),
        BottomNavItem("Mis Mascotas", "mis_mascotas", Icons.Filled.Pets),
        BottomNavItem("Mapa", "mapa", Icons.Filled.LocationOn),
        BottomNavItem("Adopciones", "adopciones", Icons.Filled.Favorite),
        BottomNavItem("Noticias", "noticias", Icons.Filled.Newspaper),
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStack by bottomNavController.currentBackStackEntryAsState()
                val current = backStack?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        selected = current == item.route,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                popUpTo("dashboard") { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "dashboard",
            modifier = Modifier.padding(padding)
        ) {
            composable("dashboard") {
                DashboardScreen(
                    onVerCarnet = { id -> navController.navigate(Screen.CarnetDetalle.withId(id)) },
                    onVerMapa = { navController.navigate(Screen.Mapa.route) },
                    onVerCampanas = { navController.navigate(Screen.Campanas.route) },
                    onPerfil = { navController.navigate(Screen.Perfil.route) }
                )
            }
            composable("mis_mascotas") {
                MisMascotasScreen(onMascotaClick = { id -> navController.navigate(Screen.CarnetDetalle.withId(id)) })
            }
            composable("mapa") {
                MapaScreen(onVeterinariaClick = { id -> navController.navigate(Screen.VeterinariaDetalle.withId(id)) })
            }
            composable("adopciones") {
                AdopcionesScreen(onBack = {})
            }
            composable("noticias") {
                NoticiasScreen(onBack = {})
            }
        }
    }
}
