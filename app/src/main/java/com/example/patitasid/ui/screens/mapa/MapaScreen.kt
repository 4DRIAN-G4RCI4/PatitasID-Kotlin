package com.example.patitasid.ui.screens.mapa

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapaScreen(onVeterinariaClick: (String) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Veterinarias cercanas") }) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            TabRow(selectedTabIndex = tabIndex) {
                Tab(selected = tabIndex == 0, onClick = { tabIndex = 0 }, text = { Text("Mapa") })
                Tab(selected = tabIndex == 1, onClick = { tabIndex = 1 }, text = { Text("Lista") })
            }

            when (tabIndex) {
                0 -> MapaPlaceholder()
                1 -> ListaVeterinarias(onVeterinariaClick)
            }
        }
    }
}

@Composable
private fun MapaPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(Icons.Filled.LocationOn, null, modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.primary)
            Text("Mapa", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Text("Google Maps se integrará aquí", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
        }
    }
}

@Composable
private fun ListaVeterinarias(onVeterinariaClick: (String) -> Unit) {
    val vets = listOf(
        Triple("VetSalud Tula", "Av. Hidalgo 45, Tula", "0.8 km"),
        Triple("Clínica Animal Plus", "Calle Reforma 12", "1.2 km"),
        Triple("VetCare Tepeji", "Blvd. Principal 88", "2.5 km"),
        Triple("MascotaSana", "Col. Centro 5", "3.1 km"),
    )
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(vets.size) { i ->
            val (nombre, direccion, dist) = vets[i]
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onVeterinariaClick("vet-$i") },
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(nombre, fontWeight = FontWeight.Bold)
                        Text(direccion, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Star, null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.primary)
                            Text(" 4.5  ·  $dist", fontSize = 12.sp)
                        }
                    }
                    Icon(Icons.Filled.LocationOn, null, tint = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
