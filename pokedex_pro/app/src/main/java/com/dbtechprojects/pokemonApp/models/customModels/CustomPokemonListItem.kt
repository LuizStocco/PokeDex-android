package com.dbtechprojects.pokemonApp.models.customModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class CustomPokemonListItem(

    val name: String,

    val id: Int? = null,

    val apiId: Int,


    val Image: String? =null,


    val type: String,



) : Parcelable
