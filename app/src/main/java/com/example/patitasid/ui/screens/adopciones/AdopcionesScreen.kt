package com.example.patitasid.ui.screens.adopciones

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdopcionesScreen(onBack: () -> Unit) {
    var filtro by remember { mutableStateOf("Todos") }
    val filtros = listOf("Todos", "Perros", "Gatos", "Otros")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Adopciones") }) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Filtros
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filtros.forEach { f ->
                    FilterChip(
                        selected = filtro == f,
                        onClick = { filtro = f },
                        label = { Text(f) }
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(6) { i ->
                    AnimalCard(
                        nombre = listOf("Rocky", "Mimi", "Bolt", "Perla", "Simba", "Nala")[i],
                        especie = if (i % 2 == 0) "Perro" else "Gato",
                        edad = "${i + 1} años"
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimalCard(nombre: String, especie: String, edad: String) {
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(if (especie == "Perro") "🐶" else "🐱", fontSize = 40.sp)
            Text(nombre, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(especie, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(edad, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(4.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Adoptar", fontSize = 12.sp) }
        }
    }
}
