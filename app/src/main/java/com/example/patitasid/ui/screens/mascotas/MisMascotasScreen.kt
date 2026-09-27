package com.example.patitasid.ui.screens.mascotas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisMascotasScreen(onMascotaClick: (String) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis mascotas") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, "Agregar mascota")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(4) { i ->
                MascotaListItem(
                    nombre = listOf("Max", "Luna", "Kira", "Toby")[i],
                    especie = listOf("Perro", "Gato", "Perro", "Gato")[i],
                    raza = listOf("Labrador", "Siamés", "Golden", "Persa")[i],
                    onClick = { onMascotaClick("mascota-$i") }
                )
            }
        }
    }
}

@Composable
private fun MascotaListItem(nombre: String, especie: String, raza: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(if (especie == "Perro") "🐶" else "🐱", fontSize = 40.sp)
            Column {
                Text(nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("$especie · $raza", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
