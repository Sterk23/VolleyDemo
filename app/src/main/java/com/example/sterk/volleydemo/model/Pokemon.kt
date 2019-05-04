package com.example.sterk.volleydemo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sterk on 5/1/2019.
 */
data class Pokemon(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String,
        var detail: PokemonDetail){
        fun getId():String{
                val split = this.url.split("/")
                return split.get(split.lastIndex-1)
        }
}





