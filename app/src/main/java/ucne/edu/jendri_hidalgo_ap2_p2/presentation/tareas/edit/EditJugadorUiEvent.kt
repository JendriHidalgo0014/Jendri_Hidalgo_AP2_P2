package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.edit

sealed interface EditJugadorUiEvent {
    data class Load(val id: Int?) : EditJugadorUiEvent
    data class NombresChanged(val value: String) : EditJugadorUiEvent
    data class EmailChanged(val value: String) : EditJugadorUiEvent
    data object Save : EditJugadorUiEvent
}