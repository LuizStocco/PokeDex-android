package com.dbtechprojects.pokemonApp.ui.activities
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dbtechprojects.pokemonApp.R
import dagger.hilt.android.AndroidEntryPoint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PokemonApp) // set theme of app once main activity has loaded
        setContentView(R.layout.activity_main)



        }
    }


