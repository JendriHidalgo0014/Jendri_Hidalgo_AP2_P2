package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListJugadorScreen(
    viewModel: ListJugadorViewModel = hiltViewModel(),
    onJugadorClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListJugadorBodyScreen(
        state = state,
        onJugadorClick = onJugadorClick,
        onAddClick = onAddClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListJugadorBodyScreen(
    state: ListJugadorUiState,
    onJugadorClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Consulta de Jugadores") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar jugador")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            ElevatedCard(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total de jugadores",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${state.jugadores.size}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            if (state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(state.jugadores) { jugador ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onJugadorClick(jugador.id) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = jugador.nombres,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = jugador.email,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListJugadorBodyScreenPreview() {
    val state = ListJugadorUiState(
        jugadores = listOf(
            Jugador(1, "Enel", "enel@gmail.com"),
            Jugador(2, "Juan", "juan@gmail.com"),
            Jugador(3, "Ramon", "ramon@gmail.com"),
        )
    )
    MaterialTheme {
        Surface {
            ListJugadorBodyScreen(
                state = state,
                onJugadorClick = {},
                onAddClick = {}
            )
        }
    }
}