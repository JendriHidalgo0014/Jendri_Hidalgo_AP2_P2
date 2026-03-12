package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.edit

data class EditJugadorUiState(
    val jugadorId: Int? = null,
    val nombres: String = "",
    val email: String = "",
    val nombresError: String? = null,
    val emailError: String? = null,
    val isSaving: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val error: String? = null
)