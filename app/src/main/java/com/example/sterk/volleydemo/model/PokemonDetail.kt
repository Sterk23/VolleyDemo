package com.example.sterk.volleydemo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sterk on 5/5/2019.
 */
data class PokemonDetail(
        @SerializedName("sprites")
        val imgUrl: PokemonImageUrl,
        @SerializedName("types")
        val types: ArrayList<PokemonType>,
        @SerializedName("stats")
        val stats: ArrayList<PokemonStat>
)