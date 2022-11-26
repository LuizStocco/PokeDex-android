package com.dbtechprojects.pokemonApp.repository

import com.dbtechprojects.pokemonApp.models.api_responses.PokemonDetailItem
import com.dbtechprojects.pokemonApp.models.customModels.CustomPokemonListItem
import com.dbtechprojects.pokemonApp.util.Resource


// interface class to be overridden by main Repository

interface DefaultRepository {
    suspend fun getPokemonList(start: Int, end: Int): Resource<List<CustomPokemonListItem>>
    suspend fun getPokemonDetail(id: Int): Resource<PokemonDetailItem>


}