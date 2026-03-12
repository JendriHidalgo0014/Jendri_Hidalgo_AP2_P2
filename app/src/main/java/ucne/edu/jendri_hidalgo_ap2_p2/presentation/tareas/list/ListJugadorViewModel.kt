package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.Resource
import ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase.GetJugadoresUseCase
import javax.inject.Inject


@HiltViewModel
class ListJugadorViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ListJugadorUiState())
    val state = _state.asStateFlow()

    init {
        loadJugadores()
    }

    fun onEvent(event: ListJugadorUiEvent) {
        when (event) {
            is ListJugadorUiEvent.UpdateFilterName -> {
                _state.update {
                    it.copy(filterName = event.nombres)
                }
            }
            ListJugadorUiEvent.Search -> loadJugadores()
        }
    }

    private fun loadJugadores() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val current = _state.value

            val result = getJugadoresUseCase(
                nombres = current.filterName.takeIf { it.isNotBlank() }
            )

            when (result) {
                is Resource.Success ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            jugadores = result.data ?: emptyList()
                        )
                    }

                is Resource.Error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }
    }
}
