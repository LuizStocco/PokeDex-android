package com.dbtechprojects.pokemonApp.models.api_responses

import com.google.gson.annotations.SerializedName

data class PokemonDetailItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,


    var timestamp: String? = "", // additional value needed to invalidate cache

    @SerializedName("abilities")
    val abilities: List<PokemonAbility>,

    @SerializedName("stats")
    val stat: List<PokemonStat>,

    @SerializedName("types")
    val types: List<PokemonType>?,

    @SerializedName("sprites")
    val sprites: Sprites
)

data class PokemonAbility(
    @SerializedName("ability")
    val ability: NameAndUrl,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    @SerializedName("slot")
    val slot: Int
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int?,

    @SerializedName("effort")
    val effort: Int?,

    @SerializedName("stat")
    val stat: NameAndUrl
)

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: NameAndUrl
)

data class Sprites(
    val front_default: String,
     @SerializedName("other") val otherSprites: OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork") val artwork: OfficialArtwork
)

data class OfficialArtwork(
    val front_default: String?
)


data class NameAndUrl(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)

