package ucne.edu.jendri_hidalgo_ap2_p2.data.remote

import android.net.http.HttpException
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.dto.JugadorDto
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.dto.JugadorResponseDto
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadoresApi
) {

    suspend fun getJugadores(
        nombres: String?,
        email: String?,
    ): Result<JugadorResponseDto> {
        try {
            val response = api.getJugadores(nombres, email)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getJugadorDetail(id: Int): Result<JugadorDto> {
        try {
            val response = api.getJugadorDetail(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }
}