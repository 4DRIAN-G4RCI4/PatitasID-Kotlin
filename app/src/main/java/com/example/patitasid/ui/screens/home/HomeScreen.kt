package com.example.patitasid.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.patitasid.ui.screens.adopciones.AdopcionesScreen
import com.example.patitasid.ui.screens.campanas.CampanasScreen
import com.example.patitasid.ui.screens.dashboard.DashboardScreen
import com.example.patitasid.ui.screens.mapa.MapaScreen
import com.example.patitasid.ui.screens.mascotas.MisMascotasScreen
import com.example.patitasid.ui.screens.noticias.NoticiasScreen
import com.example.patitasid.ui.screens.perfil.PerfilScreen
import kotlinx.coroutines.launch

// Roles disponibles
enum class Rol { Usuario, Veterinaria }

data class NavItem(val label: String, val icon: ImageVector, val route: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCarnet: (String) -> Unit,
    onVeterinaria: (String) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentRoute by remember { mutableStateOf("dashboard") }
    var rol by remember { mutableStateOf(Rol.Usuario) }

    val navItems = remember(rol) {
        buildList {
            add(NavItem("Inicio", Icons.Filled.Home, "dashboard"))
            add(NavItem("Mis mascotas", Icons.Filled.Pets, "mis_mascotas"))
            add(NavItem("Mapa / Veterinarias", Icons.Filled.LocationOn, "mapa"))
            add(NavItem("Adopciones", Icons.Filled.Favorite, "adopciones"))
            add(NavItem("Campañas", Icons.Filled.Vaccines, "campanas"))
            add(NavItem("Noticias", Icons.Filled.Newspaper, "noticias"))
            if (rol == Rol.Veterinaria) {
                add(NavItem("Panel veterinaria", Icons.Filled.MedicalServices, "panel_vet"))
            }
            add(NavItem("Mi perfil", Icons.Filled.Person, "perfil"))
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(290.dp)) {
                // Header del drawer
                Box(
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Surface(shape = CircleShape, modifier = Modifier.size(56.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(if (rol == Rol.Usuario) "🐾" else "🏥", fontSize = 24.sp)
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Text("Adrián García", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(if (rol == Rol.Usuario) "Dueño de mascotas" else "Clínica VetSalud", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

                        Spacer(Modifier.height(12.dp))

                        // Switch de rol
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Modo veterinaria", fontSize = 13.sp)
                            Switch(
                                checked = rol == Rol.Veterinaria,
                                onCheckedChange = { rol = if (it) Rol.Veterinaria else Rol.Usuario },
                                modifier = Modifier.height(24.dp)
                            )
                        }
                    }
                }

                HorizontalDivider()
                Spacer(Modifier.height(8.dp))

                // Items de navegación
                navItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            currentRoute = item.route
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }

                Spacer(Modifier.weight(1f))
                HorizontalDivider()
                NavigationDrawerItem(
                    icon = { Icon(Icons.Filled.Logout, null) },
                    label = { Text("Cerrar sesión") },
                    selected = false,
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(navItems.find { it.route == currentRoute }?.label ?: "PatitasID") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, "Menú")
                        }
                    },
                    actions = {
                        if (rol == Rol.Veterinaria) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                modifier = Modifier.padding(end = 12.dp)
                            ) {
                                Text("🏥 Vet", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                when (currentRoute) {
                    "dashboard"  -> DashboardScreen(
                        onVerCarnet = onCarnet,
                        onVerMapa = { currentRoute = "mapa" },
                        onVerCampanas = { currentRoute = "campanas" },
                        onPerfil = { currentRoute = "perfil" }
                    )
                    "mis_mascotas" -> MisMascotasScreen(onMascotaClick = onCarnet)
                    "mapa"       -> MapaScreen(onVeterinariaClick = onVeterinaria)
                    "adopciones" -> AdopcionesScreen(onBack = {})
                    "campanas"   -> CampanasScreen(onBack = {})
                    "noticias"   -> NoticiasScreen(onBack = {})
                    "perfil"     -> PerfilScreen(onBack = {})
                    "panel_vet"  -> PanelVetScreen()
                    else -> DashboardScreen(onVerCarnet = onCarnet, onVerMapa = {}, onVerCampanas = {}, onPerfil = {})
                }
            }
        }
    }
}

// Panel básico para rol veterinaria
@Composable
private fun PanelVetScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Panel Veterinaria", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text("Funciones exclusivas para clínicas", color = MaterialTheme.colorScheme.onSurfaceVariant)

        listOf(
            Triple("📋", "Catálogo de productos", "Gestiona tu inventario"),
            Triple("💰", "Punto de venta", "Registra ventas y tickets"),
            Triple("🐾", "Carnet de pacientes", "Actualiza historiales"),
            Triple("📢", "Publicar campaña", "Difunde tus campañas"),
        ).forEach { (emoji, titulo, desc) ->
            Card(shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(emoji, fontSize = 28.sp)
                    Column {
                        Text(titulo, fontWeight = FontWeight.SemiBold)
                        Text(desc, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}
