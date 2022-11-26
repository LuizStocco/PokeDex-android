package com.dbtechprojects.pokemonApp.models.api_responses

import com.google.gson.annotations.SerializedName

// CLASSE DE DADOS PARA CADA ITEM DA LISTA DE POKEMON
data class PokemonListItem(
    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val next: String,

    @SerializedName("previous")
    val previous: String,

    @SerializedName("results")
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

