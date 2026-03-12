package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    onJugadorClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListJugadorBodyScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onJugadorClick = onJugadorClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListJugadorBodyScreen(
    state: ListJugadorUiState,
    onEvent: (ListJugadorUiEvent) -> Unit,
    onJugadorClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Consulta de Jugadores") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            FilterSection(
                nombres = state.filterName,
                onEvent = onEvent
            )

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.jugadores) { jugador ->
                    JugadorItem(
                        jugador = jugador,
                        onClick = { onJugadorClick(jugador.id) }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun FilterSection(
    nombres: String,
    onEvent: (ListJugadorUiEvent) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = nombres,
                onValueChange = { onEvent(ListJugadorUiEvent.UpdateFilterName(it)) },
                label = { Text("Nombre del Jugador") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { onEvent(ListJugadorUiEvent.Search) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Buscar")
            }
        }
    }
}

@Composable
fun JugadorItem(
    jugador: Jugador,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = jugador.nombres,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListJugadorBodyScreenPreview() {
    val sampleJugadores = listOf(
        Jugador(
            id = 1,
            nombres = "Enel",
            email = "enel@gmail.com",
        ),
    )
    val state = ListJugadorUiState(
        jugadores = sampleJugadores,
        filterName = ""
    )

    MaterialTheme {
        Surface {
            ListJugadorBodyScreen(
                state = state,
                onEvent = {},
                onJugadorClick = {}
            )
        }
    }
}
