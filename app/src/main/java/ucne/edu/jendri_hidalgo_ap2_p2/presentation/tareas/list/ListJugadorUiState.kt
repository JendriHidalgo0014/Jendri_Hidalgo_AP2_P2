package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list

import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador

data class ListJugadorUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)