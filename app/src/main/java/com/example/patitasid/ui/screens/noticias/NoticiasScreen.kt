package com.example.patitasid.ui.screens.noticias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticiasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Noticias locales") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(5) { i ->
                NoticiaCard(
                    titulo = listOf(
                        "Alerta: perros extraviados en Tula centro",
                        "Feria de adopción este sábado",
                        "Consejos para el calor en mascotas",
                        "Nueva clínica veterinaria en Tepeji",
                        "Campaña de esterilización gratuita"
                    )[i],
                    categoria = listOf("Alerta", "Adopción", "Consejos", "Novedad", "Campaña")[i],
                    fecha = "hace ${i + 1} día${if (i > 0) "s" else ""}"
                )
            }
        }
    }
}

@Composable
private fun NoticiaCard(titulo: String, categoria: String, fecha: String) {
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = {}, label = { Text(categoria, fontSize = 11.sp) })
            }
            Text(titulo, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text(fecha, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
