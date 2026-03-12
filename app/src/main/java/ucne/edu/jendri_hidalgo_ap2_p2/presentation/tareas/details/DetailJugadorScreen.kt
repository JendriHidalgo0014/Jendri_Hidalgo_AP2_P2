package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador

@Composable
fun DetailJugadorScreen(
    viewModel: DetailJugadorViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailJugadorBodyScreen(
        state = state,
        onBack = onBack,
        onEdit = onEdit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailJugadorBodyScreen(
    state: DetailJugadorUiState,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Jugador") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Filled.Edit, contentDescription = "Editar")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            if (state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            state.jugador?.let { jugador ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = jugador.nombres,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Nombre:", style = MaterialTheme.typography.titleMedium)
                            Text(jugador.nombres, style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Email:", style = MaterialTheme.typography.titleMedium)
                            Text(jugador.email, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailJugadorBodyScreenPreview() {
    val state = DetailJugadorUiState(jugador = Jugador(1, "Enel", "enel@gmail.com"))
    MaterialTheme {
        Surface {
            DetailJugadorBodyScreen(state = state, onBack = {}, onEdit = {})
        }
    }
}