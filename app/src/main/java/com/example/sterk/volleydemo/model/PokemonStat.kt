package com.example.sterk.volleydemo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sterk on 5/5/2019.
 */
data class PokemonStat(
        @SerializedName("base_stat")
        val baseStat: String,
        @SerializedName("effort")
        val effort: String,
        @SerializedName("stat")
        val name: PokemonName
)