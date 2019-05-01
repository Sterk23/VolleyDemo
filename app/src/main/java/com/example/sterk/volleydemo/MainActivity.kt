package com.example.sterk.volleydemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private val TAG = "Mainactivity"
    private val url:String = "https://pokeapi.co/api/v2/pokemon"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnRequest = findViewById<Button>(R.id.buttonRequest)
        btnRequest.setOnClickListener{
            sendRequestandResponse()
        }
    }

    private fun sendRequestandResponse() {
        //initialize RequestQueue
        val mRequestQueue = Volley.newRequestQueue(this)

        //Initialize Request
        val mStringRequest = StringRequest(Request.Method.GET,url, Response.Listener<String> {response ->
            Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
        }, Response.ErrorListener { error ->
            Log.i(TAG,"Error :" + error.toString());

        })
            mRequestQueue.add(mStringRequest)
    }
}
