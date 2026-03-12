package ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.Resource
import ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase.GetJugadorDetailUseCase
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.navigation.Screen
import javax.inject.Inject


@HiltViewModel
class DetailJugadorViewModel @Inject constructor(
    private val getJugadorDetailUseCase: GetJugadorDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailJugadorUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<Screen.JugadorDetail>()
        loadJugador(args.id)
    }

    private fun loadJugador(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val result = getJugadorDetailUseCase(id)) {
                is Resource.Success ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            jugador = result.data
                        )
                    }

                is Resource.Error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                is Resource.Loading -> Unit
            }
        }
    }
}

