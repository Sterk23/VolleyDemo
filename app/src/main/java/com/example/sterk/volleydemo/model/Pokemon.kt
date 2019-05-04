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
                   var _imgProfile: Int?){
            var imgProfile: Int? = _imgProfile
                get(): Int?{
                    val urlCut = url.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                 return Integer.parseInt(urlCut[urlCut.size - 1])
            }
}

