package ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase

import kotlinx.coroutines.flow.Flow
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.Resource
import ucne.edu.jendri_hidalgo_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class CreateJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(nombres: String, email: String): Flow<Resource<Unit>> =
        repository.createJugador(nombres, email)
}