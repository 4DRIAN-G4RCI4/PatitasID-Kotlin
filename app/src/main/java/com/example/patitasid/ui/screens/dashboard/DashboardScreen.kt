package com.example.patitasid.ui.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onVerCarnet: (String) -> Unit,
    onVerMapa: () -> Unit,
    onVerCampanas: () -> Unit,
    onPerfil: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PatitasID") },
                actions = {
                    IconButton(onClick = onPerfil) { Icon(Icons.Filled.Person, "Perfil") }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Hola, Adrián 👋", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("¿Cómo están tus mascotas hoy?", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            // Mis mascotas (preview horizontal)
            item {
                Text("Mis mascotas", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(3) { i ->
                        MascotaCard(nombre = "Mascota ${i + 1}", especie = "Perro", onClick = { onVerCarnet("mascota-$i") })
                    }
                }
            }

            // Accesos rápidos
            item {
                Text("Accesos rápidos", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    AccesoCard("Veterinarias", Icons.Filled.LocationOn, Modifier.weight(1f), onVerMapa)
                    AccesoCard("Campañas", Icons.Filled.Vaccines, Modifier.weight(1f), onVerCampanas)
                }
            }

            // Recordatorios
            item {
                Text("Próximos recordatorios", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(Modifier.height(8.dp))
                RecordatorioItem("Vacuna rabia - Max", "15 oct 2026")
                RecordatorioItem("Desparasitación - Luna", "20 oct 2026")
            }
        }
    }
}

@Composable
private fun MascotaCard(nombre: String, especie: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(130.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🐶", fontSize = 36.sp)
            Spacer(Modifier.height(4.dp))
            Text(nombre, fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Text(especie, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun AccesoCard(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(modifier = modifier.clickable(onClick = onClick), shape = RoundedCornerShape(12.dp)) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(icon, contentDescription = label, tint = MaterialTheme.colorScheme.primary)
            Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun RecordatorioItem(titulo: String, fecha: String) {
    ListItem(
        headlineContent = { Text(titulo) },
        supportingContent = { Text(fecha) },
        leadingContent = { Icon(Icons.Filled.Notifications, null, tint = MaterialTheme.colorScheme.primary) }
    )
    HorizontalDivider()
}
