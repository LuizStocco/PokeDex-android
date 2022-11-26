package com.dbtechprojects.pokemonApp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.pokemonApp.models.customModels.CustomPokemonListItem
import com.dbtechprojects.pokemonApp.repository.DefaultRepository
import com.dbtechprojects.pokemonApp.util.Resource
import com.dbtechprojects.pokemonApp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {
    private val _pokemonList = SingleLiveEvent<Resource<List<CustomPokemonListItem>>>()
    val pokemonList: LiveData<Resource<List<CustomPokemonListItem>>>
        get() = _pokemonList
    private var start : Int = 1
    private var end : Int = 9

    init {
        Log.d("oncreate", "oncreate viewmodel")
        getPokemonList()
    }

// Sempre vai chamar o inicio da lista
    fun getPokemonList() {
        _pokemonList.postValue(Resource.Loading("loading"))
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonList.postValue(repository.getPokemonList(1,10))
        }
    }

    fun getNextPage(){
        viewModelScope.launch(Dispatchers.IO) {
            start = end
            end += 10
             _pokemonList.postValue(repository.getPokemonList(start,end))

        }
    }
}