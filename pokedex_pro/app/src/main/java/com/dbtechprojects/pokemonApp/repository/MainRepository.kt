package com.dbtechprojects.pokemonApp.repository

import com.dbtechprojects.pokemonApp.api.PokeApi
import com.dbtechprojects.pokemonApp.models.api_responses.PokemonDetailItem
import com.dbtechprojects.pokemonApp.models.customModels.CustomPokemonListItem
import com.dbtechprojects.pokemonApp.util.Resource
import java.lang.Exception
import javax.inject.Inject


// class to implement methods from default repository interface, query remote datasources and return results to viewModels
private const val TAG = "MainRepository"

class MainRepository @Inject constructor(
    private val pokeApi: PokeApi,
) : DefaultRepository {

    override suspend fun getPokemonList(
        start: Int,
        end: Int
    ): Resource<List<CustomPokemonListItem>> {

        // if return null then preSeed from Constants
        val pokemonList = mutableListOf<CustomPokemonListItem>()
        for (i in start..end) {
            when (val apiResult = getPokemonDetail(i)) {
                is Resource.Success -> {

                    if (apiResult.data != null) {
                        apiResult.data.let { newPokemon ->
                            // create custom pokemon object save in DB
                            val newPokemonObj = CustomPokemonListItem(
                                name = newPokemon.name,
                                Image = newPokemon.sprites.front_default,
                                type = newPokemon.types?.get(0)?.type?.name.toString(),
                                apiId = newPokemon.id
                            )
                            pokemonList.add(newPokemonObj)

                        }
                    } else {
                        return Resource.Error("unable to retrieve next items")
                    }
                }
                else -> return Resource.Error("unable to retrieve next items")

            }
        }



        return Resource.Success(pokemonList)


    }


    override suspend fun getPokemonDetail(id: Int): Resource<PokemonDetailItem> {
        try {
            val apiResult = pokeApi.getPokemonDetail(id)
            if (apiResult.isSuccessful) {

                if (apiResult.body() != null) {

                    // add timestamp
                    val newPokemon = apiResult.body()
                    newPokemon!!.timestamp = System.currentTimeMillis().toString()


                    // return from DB
                    return Resource.Success(newPokemon!!)
                }
            } else {
                return Resource.Error(apiResult.message())
            }
        } catch (e: Exception) {
            return Resource.Error("error retrieving results")
        }
        return Resource.Error("error retrieving results")
    }


}