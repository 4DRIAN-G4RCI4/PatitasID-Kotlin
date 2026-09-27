package com.example.patitasid.navigation

sealed class Screen(val route: String) {
    // Auth
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")

    // Main (bottom nav)
    object Home : Screen("home")
    object MisMascotas : Screen("mis_mascotas")
    object Mapa : Screen("mapa")
    object Adopciones : Screen("adopciones")
    object Noticias : Screen("noticias")

    // Detalle
    object CarnetDetalle : Screen("carnet/{mascotaId}") {
        fun withId(id: String) = "carnet/$id"
    }
    object VeterinariaDetalle : Screen("veterinaria/{vetId}") {
        fun withId(id: String) = "veterinaria/$id"
    }
    object Campanas : Screen("campanas")
    object Perfil : Screen("perfil")
}
