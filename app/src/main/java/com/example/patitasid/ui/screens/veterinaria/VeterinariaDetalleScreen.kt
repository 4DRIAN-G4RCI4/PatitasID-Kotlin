package com.example.patitasid.ui.screens.veterinaria

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinariaDetalleScreen(vetId: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VetSalud Tula") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, null) } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("VetSalud Tula", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Row { Icon(Icons.Filled.LocationOn, null, modifier = Modifier.size(16.dp)); Spacer(Modifier.width(4.dp)); Text("Av. Hidalgo 45, Tula de Allende", fontSize = 13.sp) }
                        Row { Icon(Icons.Filled.AccessTime, null, modifier = Modifier.size(16.dp)); Spacer(Modifier.width(4.dp)); Text("Lun–Vie 9:00–18:00  ·  Sáb 9:00–14:00", fontSize = 13.sp) }
                        Row { Icon(Icons.Filled.Phone, null, modifier = Modifier.size(16.dp)); Spacer(Modifier.width(4.dp)); Text("773 123 4567", fontSize = 13.sp) }
                        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Cómo llegar") }
                    }
                }
            }

            item {
                Text("Servicios", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                Spacer(Modifier.height(8.dp))
                listOf("Consulta general – \$250", "Vacunación – \$150", "Estética – \$200", "Cirugía – desde \$800").forEach { s ->
                    ListItem(headlineContent = { Text(s) }, leadingContent = { Icon(Icons.Filled.Pets, null) })
                    HorizontalDivider()
                }
            }

            item {
                Text("Productos disponibles", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                Spacer(Modifier.height(8.dp))
                listOf("Alimento Premium 15kg – \$450", "Desparasitante – \$80", "Collar antipulgas – \$120").forEach { p ->
                    ListItem(headlineContent = { Text(p) }, leadingContent = { Icon(Icons.Filled.ShoppingBag, null) })
                    HorizontalDivider()
                }
            }
        }
    }
}
