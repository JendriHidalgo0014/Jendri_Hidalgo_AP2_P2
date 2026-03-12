package ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase

import ucne.edu.jendri_hidalgo_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadoresUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(
        nombres: String? = null,
        email: String? = null
    ) = repository.getJugadores(nombres, email)
}