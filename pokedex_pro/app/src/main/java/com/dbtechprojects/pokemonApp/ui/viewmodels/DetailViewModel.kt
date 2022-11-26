package com.dbtechprojects.pokemonApp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.pokemonApp.models.api_responses.PokemonDetailItem
import com.dbtechprojects.pokemonApp.models.customModels.CustomPokemonListItem
import com.dbtechprojects.pokemonApp.repository.DefaultRepository
import com.dbtechprojects.pokemonApp.util.Resource
import com.dbtechprojects.pokemonApp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    // single live event to stop stale items being stored in Live Data
    private val _pokemonDetails = SingleLiveEvent<Resource<PokemonDetailItem>>()
    val pokemonDetails: LiveData<Resource<PokemonDetailItem>>
        get() = _pokemonDetails


    fun getPokemonDetails(id: Int) {
        _pokemonDetails.postValue(Resource.Loading("loading"))
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonDetails.postValue(repository.getPokemonDetail(id))
        }
    }



}