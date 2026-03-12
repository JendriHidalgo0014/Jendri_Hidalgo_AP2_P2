package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditJugadorScreen(
    jugadorId: Int?,
    goBack: () -> Unit,
    viewModel: EditJugadorViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(jugadorId) {
        viewModel.onEvent(EditJugadorUiEvent.Load(jugadorId))
    }

    LaunchedEffect(state.saved) {
        if (state.saved) goBack()
    }

    EditJugadorBody(
        state = state,
        onEvent = viewModel::onEvent,
        goBack = goBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditJugadorBody(
    state: EditJugadorUiState,
    onEvent: (EditJugadorUiEvent) -> Unit,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.isNew) "Nuevo Jugador" else "Modificar Jugador") },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = state.nombres,
                onValueChange = { onEvent(EditJugadorUiEvent.NombresChanged(it)) },
                label = { Text("Nombres") },
                isError = state.nombresError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (state.nombresError != null) {
                Text(state.nombresError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = { onEvent(EditJugadorUiEvent.EmailChanged(it)) },
                label = { Text("Email") },
                isError = state.emailError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (state.emailError != null) {
                Text(state.emailError, color = MaterialTheme.colorScheme.error)
            }

            if (state.error != null) {
                Spacer(Modifier.height(8.dp))
                Text(state.error, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { onEvent(EditJugadorUiEvent.Save) },
                enabled = !state.isSaving,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (state.isSaving) "Guardando..." else "Guardar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditJugadorBodyPreview() {
    MaterialTheme {
        EditJugadorBody(
            state = EditJugadorUiState(),
            onEvent = {},
            goBack = {}
        )
    }
}