package com.example.sterk.volleydemo

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sterk.volleydemo.model.ListPokemonItem
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val TAG = "Mainactivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        sendRequestandResponse(progressBar)
    }
    private val url:String ="https://pokeapi.co/api/v2/pokemon?offset=20&limit=20"
    private fun sendRequestandResponse( progressBar:ProgressBar) {
        //initialize RequestQueue
        val mRequestQueue = Volley.newRequestQueue(this)

        //Initialize Request
        val mJsonRequest = StringRequest(Request.Method.GET,url, Response.Listener<String> { response ->
//            Toast.makeText(this,"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            getListFromReponse(response)
        }, Response.ErrorListener { error ->

        })
        mRequestQueue.add(mJsonRequest)
        mRequestQueue.addRequestFinishedListener(RequestQueue.RequestFinishedListener<String> {
            if (progressBar != null )
                progressBar.visibility = View.GONE
        })

    }
    private fun getListFromReponse(response: String?) {
        val list = Gson().fromJson(response,ListPokemonItem::class.java)
        val pokemonList = list.members
        var pokemonName:String? = ""
        for(pokemon in pokemonList){
            pokemonName = pokemonName + pokemon.name+"\n"
        }
    val textViewPokemonName = findViewById<TextView>(R.id.textView_name)
        textViewPokemonName.text = pokemonName

    }
}
