package com.example.patitasid.ui.screens.carnet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarnetDetalleScreen(mascotaId: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carnet digital") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, null) } },
                actions = { IconButton(onClick = {}) { Icon(Icons.Filled.QrCode, "Ver QR") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header mascota
            item {
                Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🐶", fontSize = 64.sp)
                        Spacer(Modifier.height(8.dp))
                        Text("Max", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text("Labrador · 3 años · Macho", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(4.dp))
                        AssistChip(onClick = {}, label = { Text("Esterilizado") })
                    }
                }
            }

            // Datos generales
            item {
                SeccionCarnet("Datos generales") {
                    DatoRow("Microchip", "123456789")
                    DatoRow("Color", "Dorado")
                    DatoRow("Alergias", "Ninguna conocida")
                    DatoRow("Tipo de sangre", "DEA 1.1+")
                }
            }

            // Vacunas
            item {
                SeccionCarnet("Vacunación") {
                    VacunaItem("Rabia", "12 mar 2025", "12 mar 2026")
                    VacunaItem("Parvovirus", "12 mar 2025", "12 mar 2026")
                    VacunaItem("Moquillo", "12 mar 2025", "12 mar 2026")
                }
            }

            // Desparasitaciones
            item {
                SeccionCarnet("Desparasitaciones") {
                    VacunaItem("Externa", "01 ago 2025", "01 nov 2025")
                    VacunaItem("Interna", "01 ago 2025", "01 feb 2026")
                }
            }

            // Consultas
            item {
                SeccionCarnet("Últimas consultas") {
                    ConsultaItem("Revisión general", "Clínica VetSalud", "15 sep 2025")
                    ConsultaItem("Herida pata delantera", "Clínica VetSalud", "02 ago 2025")
                }
            }
        }
    }
}

@Composable
private fun SeccionCarnet(titulo: String, content: @Composable ColumnScope.() -> Unit) {
    Text(titulo, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
    Spacer(Modifier.height(6.dp))
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) { content() }
    }
}

@Composable
private fun DatoRow(label: String, valor: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
        Text(valor, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}

@Composable
private fun VacunaItem(nombre: String, aplicada: String, proxima: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(nombre, fontWeight = FontWeight.Medium)
        Text("Aplicada: $aplicada  ·  Próxima: $proxima", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    HorizontalDivider()
}

@Composable
private fun ConsultaItem(motivo: String, clinica: String, fecha: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(motivo, fontWeight = FontWeight.Medium)
        Text("$clinica · $fecha", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    HorizontalDivider()
}
