package com.example.patitasid.navigation

sealed class Screen(val route: String) {
    object Splash      : Screen("splash")
    object Login       : Screen("login")
    object Demo        : Screen("demo")
    object Register    : Screen("register")
    object Home        : Screen("home")
    object CarnetDetalle : Screen("carnet/{mascotaId}") {
        fun withId(id: String) = "carnet/$id"
    }
    object VeterinariaDetalle : Screen("veterinaria/{vetId}") {
        fun withId(id: String) = "veterinaria/$id"
    }
}
