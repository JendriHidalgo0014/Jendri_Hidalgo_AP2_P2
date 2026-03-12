package ucne.edu.jendri_hidalgo_ap2_p2.data.remote.dto

import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador

data class JugadorResponseDto(
    val items: List<JugadorDto>
)
data class JugadorDto(
    val id: Int,
    val nombres: String,
    val email: String,
) {
    fun toDomain() = Jugador(
        id, nombres, email,
    )
}
