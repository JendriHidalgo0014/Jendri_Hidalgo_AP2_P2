package ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase

import ucne.edu.jendri_hidalgo_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadorDetailUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int) = repository.getJugadorDetail(id)
}
