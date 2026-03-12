package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list

sealed interface ListJugadorUiEvent {
    data class UpdateFilterName(val nombres: String) : ListJugadorUiEvent
    data object Search : ListJugadorUiEvent
}