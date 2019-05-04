package com.example.sterk.volleydemo.model

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Member

/**
 * Created by sterk on 5/1/2019.
 */
data class ListPokemonItem(
        @SerializedName("results")
        val members: ArrayList<Pokemon>,
                           @SerializedName("next") val next: String,
        @SerializedName("previos") val previous: String)