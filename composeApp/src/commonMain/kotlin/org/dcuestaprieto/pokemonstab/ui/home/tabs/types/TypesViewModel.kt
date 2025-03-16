package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.dcuestaprieto.pokemonstab.data.remote.ApiService
import org.dcuestaprieto.pokemonstab.domain.GetRandomType

class TypesViewModel(private val getRandomType: GetRandomType, apiService: ApiService) : ViewModel() {
    private val _state = MutableStateFlow(TypesState())
    val state: StateFlow<TypesState> = _state

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getRandomType()
            }
            val typesList = withContext(Dispatchers.IO){
                apiService.getAllTypes()
            }
            _state.update { it.copy(randomType = result, typesList = typesList.results) }
        }
    }
}