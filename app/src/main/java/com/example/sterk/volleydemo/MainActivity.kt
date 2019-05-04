package com.example.sterk.volleydemo

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sterk.volleydemo.adapter.PokemonAdapter
import com.example.sterk.volleydemo.adapter.PokemonListener
import com.example.sterk.volleydemo.api.VolleyCallback
import com.example.sterk.volleydemo.model.ListPokemonItem
import com.example.sterk.volleydemo.model.Pokemon
import com.example.sterk.volleydemo.model.PokemonDetail
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject



class MainActivity : AppCompatActivity(), PokemonListener {


    private val TAG = "Mainactivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var pokemonList:ArrayList<Pokemon>
        getPokemonList(progressBar, object: VolleyCallback<ArrayList<Pokemon>>{
            override fun onSuccess(list: ArrayList<Pokemon>) {
//                for (pokemon in list) {
//                    getDetailPokemon(pokemon)
//                }
                val pokemonAdapter = PokemonAdapter(list, null)
                recyclerView.apply {
                    layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
                    isNestedScrollingEnabled = false
                    adapter = pokemonAdapter
                    onFlingListener = null

                }

                pokemonAdapter.notifyDataSetChanged()
//                progressBar.visibility = View.VISIBLE
            }
        })


    }

    private val url: String = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20"
    private fun getPokemonList(progressBar: ProgressBar,callback: VolleyCallback<ArrayList<Pokemon>>) {
        //initialize RequestQueue
        val mRequestQueue = Volley.newRequestQueue(this)
        //Initialize Request
        val mJsonRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            //            Toast.makeText(this,"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            val list = Gson().fromJson(response, ListPokemonItem::class.java)
            val pokemonList = list.members
            callback.onSuccess(pokemonList)
        }, Response.ErrorListener { error ->

        })
        mRequestQueue.add(mJsonRequest)
        mRequestQueue.addRequestFinishedListener(RequestQueue.RequestFinishedListener<String> {
            if (progressBar != null)
                progressBar.visibility = View.GONE
        })
    }

    private fun getListFromReponse(response: String?) {
        val list = Gson().fromJson(response, ListPokemonItem::class.java)
        val pokemonList = list.members
        var pokemonName: String? = ""
        for (pokemon in pokemonList) {
            getDetailPokemon(pokemon)
        }

    }

    private fun getDetailPokemon(pokemon: Pokemon) {
        val mRequestQueue = Volley.newRequestQueue(this)

        //Initialize Request
        val mJsonRequest = StringRequest(Request.Method.GET, pokemon.url, Response.Listener<String> { response ->
            pokemon.detail = Gson().fromJson(response, PokemonDetail::class.java)
        }, Response.ErrorListener { error ->

        })
        mRequestQueue.add(mJsonRequest)
        mRequestQueue.addRequestFinishedListener(RequestQueue.RequestFinishedListener<String> {
            if (progressBar != null)
                progressBar.visibility = View.GONE
        })

    }


    override fun onItemClick() {

    }
}
