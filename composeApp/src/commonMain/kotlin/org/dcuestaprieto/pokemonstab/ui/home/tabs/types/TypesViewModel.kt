package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.dcuestaprieto.pokemonstab.data.PokeApiTypeRepository
import org.dcuestaprieto.pokemonstab.data.remote.ApiService
import org.dcuestaprieto.pokemonstab.domain.TypesRepository

class TypesViewModel(apiService: ApiService, pokeApiTypeRepository: TypesRepository) : ViewModel() {
    private val _state by lazy { MutableStateFlow(TypesState()) }
    val state: StateFlow<TypesState> = _state
        .onStart { apiService.getAllTypes() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            TypesState()
        )

    init {
        viewModelScope.launch {
            val typesList = withContext(Dispatchers.IO) {
                apiService.getAllTypes()
            }
            _state.update { it.copy(typesList = typesList.results) }
        }
    }
}