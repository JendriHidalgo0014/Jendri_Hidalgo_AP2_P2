package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.Resource
import ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase.CreateJugadorUseCase
import ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase.GetJugadorDetailUseCase
import ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase.UpdateJugadorUseCase
import ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase.validateEmail
import ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase.validateNombres
import javax.inject.Inject

@HiltViewModel
class EditJugadorViewModel @Inject constructor(
    private val getJugadorDetailUseCase: GetJugadorDetailUseCase,
    private val createJugadorUseCase: CreateJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditJugadorUiState())
    val state: StateFlow<EditJugadorUiState> = _state.asStateFlow()

    fun onEvent(event: EditJugadorUiEvent) {
        when (event) {
            is EditJugadorUiEvent.Load -> onLoad(event.id)
            is EditJugadorUiEvent.NombresChanged -> _state.update {
                it.copy(nombres = event.value, nombresError = null)
            }
            is EditJugadorUiEvent.EmailChanged -> _state.update {
                it.copy(email = event.value, emailError = null)
            }
            EditJugadorUiEvent.Save -> onSave()
        }
    }

    private fun onLoad(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, jugadorId = null) }
            return
        }
        viewModelScope.launch {
            getJugadorDetailUseCase(id).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        val jugador = result.data
                        if (jugador != null) {
                            _state.update {
                                it.copy(
                                    isNew = false,
                                    jugadorId = jugador.id,
                                    nombres = jugador.nombres,
                                    email = jugador.email
                                )
                            }
                        }
                    }
                    is Resource.Error -> _state.update { it.copy(error = result.message) }
                }
            }
        }
    }

    private fun onSave() {
        val nombres = _state.value.nombres
        val email = _state.value.email

        val n = validateNombres(nombres)
        val e = validateEmail(email)

        if (!n.isValid || !e.isValid) {
            _state.update { it.copy(nombresError = n.error, emailError = e.error) }
            return
        }

        viewModelScope.launch {
            val id = _state.value.jugadorId
            val flow = if (id == null || id == 0) {
                createJugadorUseCase(nombres, email)
            } else {
                updateJugadorUseCase(id, nombres, email)
            }

            flow.collectLatest { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isSaving = true) }
                    is Resource.Success -> _state.update { it.copy(isSaving = false, saved = true) }
                    is Resource.Error -> _state.update {
                        it.copy(isSaving = false, error = result.message)
                    }
                }
            }
        }
    }
}