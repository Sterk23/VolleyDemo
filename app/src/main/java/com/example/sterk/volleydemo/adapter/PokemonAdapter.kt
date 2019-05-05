package com.example.sterk.volleydemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sterk.volleydemo.R
import com.example.sterk.volleydemo.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

/**
 * Created by sterk on 5/5/2019.
 */
class PokemonAdapter(private val items: ArrayList<Pokemon>?,
                     private val listener: PokemonListener?) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_pokemon,parent,false))
    }

    override fun getItemCount() = items!!.size
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(items!![position])
        holder.itemView.setOnClickListener{listener?.onItemClick()}
    }

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        fun bind(pokemon: Pokemon){
            itemView.apply {
                Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getId()+".png").into(itemView.imgPokemon)
                textPokemonName.text = pokemon.name.capitalize()
                textPokemonType.text = "No."+pokemon.getId()
                var types:String=""
                for(poketypes in pokemon.detail.types){
                 types += poketypes.type.name + " "
                }
                textPokemonType.text = types
            }
        }
    }

}