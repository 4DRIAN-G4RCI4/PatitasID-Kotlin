package com.example.patitasid.ui.screens.campanas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampanasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Campañas de vacunación") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, null) } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(3) { i ->
                CampanaCard(
                    titulo = listOf("Antirrábica gratuita", "Desparasitación masiva", "Esterilización")[i],
                    fecha = listOf("20 oct 2026", "5 nov 2026", "15 nov 2026")[i],
                    lugar = listOf("Palacio Municipal Tula", "Parque Central Tepeji", "Clínica VetSalud")[i],
                    gratuita = i != 2
                )
            }
        }
    }
}

@Composable
private fun CampanaCard(titulo: String, fecha: String, lugar: String, gratuita: Boolean) {
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(titulo, fontWeight = FontWeight.Bold)
                if (gratuita) Badge { Text("Gratis") }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(Icons.Filled.CalendarMonth, null, modifier = Modifier.size(16.dp))
                Text(fecha, fontSize = 13.sp)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(Icons.Filled.LocationOn, null, modifier = Modifier.size(16.dp))
                Text(lugar, fontSize = 13.sp)
            }
            OutlinedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("Registrarme")
            }
        }
    }
}
