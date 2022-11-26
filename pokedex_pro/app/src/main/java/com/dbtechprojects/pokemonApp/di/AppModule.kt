package com.dbtechprojects.pokemonApp.di

import android.content.Context
import com.dbtechprojects.pokemonApp.api.PokeApi

import com.dbtechprojects.pokemonApp.repository.DefaultRepository
import com.dbtechprojects.pokemonApp.repository.MainRepository
import com.dbtechprojects.pokemonApp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Hilt Module Class for providing dependencies to be used in repository, viewmodels and workmanager
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokeApi(): PokeApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApi::class.java)



    @Singleton
    @Provides
    fun provideMainRepository(api: PokeApi): DefaultRepository = MainRepository(api)

}