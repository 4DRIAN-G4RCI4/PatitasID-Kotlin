package com.example.patitasid.ui.screens.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun PerfilScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi perfil") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, null) } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(shape = CircleShape, modifier = Modifier.size(80.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                Box(contentAlignment = Alignment.Center) {
                    Text("👤", fontSize = 36.sp)
                }
            }
            Text("Adrián García", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("adrianadonaigarciajimenez171@gmail.com", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

            HorizontalDivider()

            listOf(
                Pair(Icons.Filled.Edit, "Editar perfil"),
                Pair(Icons.Filled.Notifications, "Notificaciones"),
                Pair(Icons.Filled.Lock, "Cambiar contraseña"),
                Pair(Icons.Filled.Help, "Ayuda"),
                Pair(Icons.Filled.Logout, "Cerrar sesión"),
            ).forEach { (icon, label) ->
                ListItem(
                    headlineContent = { Text(label) },
                    leadingContent = { Icon(icon, null) },
                    trailingContent = { Icon(Icons.Filled.ChevronRight, null) },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}
