package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.details

import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador

data class DetailJugadorUiState(
    val isLoading: Boolean = false,
    val jugador: Jugador? = null,
    val error: String? = null
)