package com.example.patitasid.ui.screens.demo

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// Demo simulada de la función core: el carnet digital de una mascota

@Composable
fun DemoScreen(onRegister: () -> Unit, onBack: () -> Unit) {
    var step by remember { mutableIntStateOf(0) }

    when (step) {
        0 -> DemoIntroStep(onNext = { step = 1 }, onBack = onBack)
        1 -> DemoFiltroStep(onNext = { step = 2 }, onBack = { step = 0 })
        2 -> DemoCarnetStep(onNext = { step = 3 }, onBack = { step = 1 })
        3 -> DemoCTAStep(onRegister = onRegister, onBack = { step = 2 })
    }
}

// Paso 0: intro visual
@Composable
private fun DemoIntroStep(onNext: () -> Unit, onBack: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.92f, targetValue = 1.08f, label = "scale",
        animationSpec = infiniteRepeatable(tween(1000, easing = FastOutSlowInEasing), RepeatMode.Reverse)
    )

    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF1A237E), Color(0xFF4A148C)))
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size((160 * scale).dp).clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text("🐾", fontSize = (72 * scale).sp)
            }

            Spacer(Modifier.height(32.dp))
            Text("¿Tienes mascota?", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
            Spacer(Modifier.height(12.dp))
            Text("En 3 pasos te mostramos cómo\nPatitasID cuida su salud por ti.", fontSize = 16.sp, color = Color.White.copy(alpha = 0.85f), textAlign = TextAlign.Center, lineHeight = 24.sp)

            Spacer(Modifier.height(48.dp))
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A237E))
            ) {
                Text("Ver cómo funciona", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(Modifier.height(12.dp))
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Volver al inicio", color = Color.White.copy(alpha = 0.7f))
            }
        }
    }
}

// Paso 1: filtro con datos del usuario — impacto visual
@Composable
private fun DemoFiltroStep(onNext: () -> Unit, onBack: () -> Unit) {
    var especie by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    val completo = especie.isNotBlank() && nombre.isNotBlank()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()).padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        Text("Cuéntanos sobre\ntu mascota 🐶", fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 34.sp)
        Text("Personalizamos todo según sus datos", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(Modifier.height(28.dp))

        // Selector especie visual
        Text("¿Qué tipo de mascota tienes?", fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf("🐶" to "Perro", "🐱" to "Gato", "🐰" to "Otro").forEach { (emoji, label) ->
                val selected = especie == label
                Card(
                    onClick = { especie = label },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    border = if (selected) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
                ) {
                    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(emoji, fontSize = 32.sp)
                        Text(label, fontSize = 12.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de tu mascota") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Text("🏷️", fontSize = 18.sp) }
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad aproximada (años)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Text("🎂", fontSize = 18.sp) }
        )

        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onNext,
            enabled = completo,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Ver el carnet de ${if (nombre.isNotBlank()) nombre else "tu mascota"}", fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(8.dp))
        TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Atrás")
        }
        Spacer(Modifier.height(24.dp))
    }
}

// Paso 2: carnet simulado — el impacto visual principal
@Composable
private fun DemoCarnetStep(onNext: () -> Unit, onBack: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(200); visible = true }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()).padding(24.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        AnimatedVisibility(visible, enter = fadeIn() + slideInVertically { -40 }) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Así se vería el carnet\ndigital de tu mascota ✨", fontSize = 22.sp, fontWeight = FontWeight.Bold, lineHeight = 30.sp)

                // Carnet card principal
                Card(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Column {
                                Text("🐾 PatitasID", fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f))
                                Text("Max", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                                Text("Golden Retriever · 3 años · Macho", fontSize = 13.sp, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f))
                            }
                            Box(
                                modifier = Modifier.size(64.dp).clip(CircleShape).background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) { Text("🐶", fontSize = 32.sp) }
                        }
                        Spacer(Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            StatChip("💉 Vacunas", "Al día", Color(0xFF4CAF50))
                            StatChip("🦠 Despar.", "Al día", Color(0xFF4CAF50))
                            StatChip("⚖️ Peso", "28.5 kg", Color(0xFF2196F3))
                        }
                    }
                }

                // Vacunas
                SeccionDemo("💉 Vacunación") {
                    VacunaDemo("Rabia antirrábica", "✅ Al día", "Mar 2026")
                    VacunaDemo("Parvovirus", "✅ Al día", "Mar 2026")
                    VacunaDemo("Moquillo", "⚠️ Próxima", "Oct 2026")
                }

                // Alertas
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
                ) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("🔔", fontSize = 24.sp)
                        Column {
                            Text("Recordatorio próximo", fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
                            Text("Vacuna Moquillo vence en 15 días", fontSize = 13.sp, color = Color(0xFFE65100).copy(alpha = 0.8f))
                        }
                    }
                }

                // Bloqueo simulado
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("🔒", fontSize = 32.sp)
                        Text("Historial completo, QR, veterinarias y más", fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                        Text("Regístrate gratis para acceder a todo", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth().height(52.dp), shape = RoundedCornerShape(14.dp)) {
            Text("Quiero esto para mi mascota 🐾", fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(8.dp))
        TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Atrás") }
        Spacer(Modifier.height(24.dp))
    }
}

// Paso 3: CTA de registro
@Composable
private fun DemoCTAStep(onRegister: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF1B5E20), Color(0xFF2E7D32)))
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🎉", fontSize = 64.sp)
            Spacer(Modifier.height(16.dp))
            Text("¡Es 100% gratis!", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
            Spacer(Modifier.height(12.dp))
            Text("Carnet digital, historial clínico,\nrecordatorios y veterinarias cercanas.", fontSize = 16.sp, color = Color.White.copy(alpha = 0.9f), textAlign = TextAlign.Center, lineHeight = 24.sp)

            Spacer(Modifier.height(40.dp))

            listOf("✅  Carnet digital siempre en tu celular", "✅  Recordatorios de vacunas automáticos", "✅  Veterinarias cerca de ti en el mapa", "✅  QR de tu mascota en emergencias").forEach {
                Text(it, color = Color.White.copy(alpha = 0.9f), fontSize = 15.sp, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
            }

            Spacer(Modifier.height(40.dp))
            Button(
                onClick = onRegister,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1B5E20))
            ) {
                Text("Crear cuenta gratis", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }
            Spacer(Modifier.height(12.dp))
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Seguir explorando", color = Color.White.copy(alpha = 0.7f))
            }
        }
    }
}

@Composable
private fun StatChip(label: String, valor: String, color: Color) {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.12f)).padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f))
        Text(valor, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
private fun SeccionDemo(titulo: String, content: @Composable ColumnScope.() -> Unit) {
    Text(titulo, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
    Spacer(Modifier.height(6.dp))
    Card(shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) { content() }
    }
}

@Composable
private fun VacunaDemo(nombre: String, estado: String, fecha: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(nombre, fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Text("Vence: $fecha", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(estado, fontSize = 13.sp)
    }
    HorizontalDivider()
}
